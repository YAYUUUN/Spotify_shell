package harmonix.app.view

import harmonix.app.model.YouTubeRepository
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
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
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                containerGenres.visibility = View.GONE
                containerSearch.visibility = View.VISIBLE
            }
        }

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
    }

    private fun searchVideos(query: String) {
        youtubeRepo.search(query, onSuccess = { videos ->
            showSearchResults(videos)
        }, onError = { error ->
            Log.e("SearchFragment", "Error searching videos: $error")
        })
    }

    private fun showSearchResults(videos: List<VideoItem>) {
        val resultsView = layoutInflater.inflate(R.layout.search_results, containerSearch, false)
        containerSearch.removeAllViews()
        containerSearch.addView(resultsView)

        // Ensure this is a RecyclerView in the layout file
        searchResultsRecyclerView = resultsView.findViewById(R.id.search_results_recycler_view)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        resultsAdapter = SearchResultsAdapter(videos)
        searchResultsRecyclerView.adapter = resultsAdapter
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
            itemView.setOnClickListener { onItemClick(query) }
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

class SearchResultsAdapter(private var results: List<VideoItem>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.search_result_title)
        val channel: TextView = view.findViewById(R.id.search_result_channel)
        val thumbnail: ImageView = view.findViewById(R.id.search_result_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = results[position]
        holder.title.text = video.snippet.title
        holder.channel.text = video.snippet.description

        Glide.with(holder.itemView.context)
            .load(video.snippet.thumbnails.medium.url)
            .into(holder.thumbnail)
    }

    override fun getItemCount() = results.size

    fun updateResults(newResults: List<VideoItem>) {
        results = newResults
        notifyDataSetChanged()
    }
}
