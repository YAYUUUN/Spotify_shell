package harmonix.app.view

import harmonix.app.model.YouTubeRepository
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import harmonix.app.R
import harmonix.app.model.VideoItem

class SearchFragment : Fragment() {

    private lateinit var searchEditText: TextInputEditText
    private lateinit var containerGenres: FrameLayout
    private lateinit var containerSearch: FrameLayout
    private lateinit var searchResultsRecyclerView: RecyclerView
    private lateinit var resultsAdapter: SearchResultsAdapter

    private val youtubeRepo = YouTubeRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchEditText = view.findViewById(R.id.fragment_search_searchBar)
        containerGenres = view.findViewById(R.id.containerGenres)
        containerSearch = view.findViewById(R.id.containerSearch)

        setupSearchBehavior()
        return view
    }

    private fun setupSearchBehavior() {
        // Mostrar/ocultar contenedores al enfocar el campo de búsqueda
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                containerGenres.visibility = View.GONE
                containerSearch.visibility = View.VISIBLE
            }
        }

        /*
        // Código anterior: Búsqueda automática mientras se escribe
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    searchVideos(query)
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        */

        // Nueva lógica: Búsqueda al presionar el botón de búsqueda del teclado
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH) {
                val query = searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    searchVideos(query)
                } else {
                    showToast("Ingresa un término de búsqueda")
                }
                true // Indica que la acción ha sido manejada
            } else {
                false
            }
        }
    }

    private fun searchVideos(query: String) {
        youtubeRepo.search(query, onSuccess = { videos ->
            showSearchResults(videos)
        }, onError = { error ->
            Log.e("SearchFragment", "Error searching videos: $error")
            showToast("Error al buscar videos: $error")
        })
    }

    private fun showSearchResults(videos: List<VideoItem>) {
        val resultsView = layoutInflater.inflate(R.layout.search_results, containerSearch, false)
        containerSearch.removeAllViews()
        containerSearch.addView(resultsView)

        searchResultsRecyclerView = resultsView.findViewById(R.id.search_results_recycler_view)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        resultsAdapter = SearchResultsAdapter(videos) { video ->
            showToast("Seleccionado: ${video.snippet.title}")
        }
        searchResultsRecyclerView.adapter = resultsAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

// Adapters
class RecentSearchedAdapter(
    private val searches: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchedAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.recent_search_text)

        fun bind(query: String) {
            textView.text = query
            itemView.setOnClickListener {
                onItemClick(query)
                showToast("Búsqueda reciente: $query")
            }
        }

        private fun showToast(message: String) {
            Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recent_searched, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searches[position])
    }

    override fun getItemCount() = searches.size
}

class SearchResultsAdapter(
    private var results: List<VideoItem>,
    private val onItemClick: (VideoItem) -> Unit
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.search_result_title)
        val channel: TextView = view.findViewById(R.id.search_result_channel)
        val thumbnail: ImageView = view.findViewById(R.id.search_result_thumbnail)

        fun bind(video: VideoItem) {
            title.text = video.snippet.title
            channel.text = video.snippet.description

            Glide.with(itemView.context)
                .load(video.snippet.thumbnails.medium.url)
                .into(thumbnail)

            itemView.setOnClickListener {
                onItemClick(video)
                showToast("Seleccionado: ${video.snippet.title}")
            }
        }

        private fun showToast(message: String) {
            Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size

    fun updateResults(newResults: List<VideoItem>) {
        results = newResults
        notifyDataSetChanged()
    }
}