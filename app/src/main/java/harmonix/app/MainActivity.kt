package harmonix.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VideoAdapter

    // Configuración de Retrofit para la API de YouTube
    private val youtubeApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YouTubeApi::class.java)
    }

    private val apiKey = "AIzaSyCGktDigD5L3pyWfthua2zaU7vBVF6xKfY" // Reemplaza con tu API key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.resultsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = VideoAdapter { video ->
            // Acción cuando se pulsa el botón de descarga
            downloadVideoAudio(video)
        }
        recyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            if (query.isNotEmpty()) {
                performSearch(query)
            }
        }
    }

    private fun performSearch(query: String) {
        // Se lanza una corrutina para realizar la búsqueda
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = youtubeApi.searchVideos(
                    part = "snippet",
                    query = query,
                    apiKey = apiKey,
                    type = "video"
                )
                if (response.isSuccessful) {
                    val searchResponse = response.body()
                    val videos = searchResponse?.items ?: emptyList()
                    withContext(Dispatchers.Main) {
                        adapter.submitList(videos)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Error: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Excepción: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun downloadVideoAudio(video: VideoItem) {
        // Función simplificada que simula la descarga (cacheo temporal) del audio
        Toast.makeText(this, "Iniciando descarga de: ${video.snippet.title}", Toast.LENGTH_SHORT).show()
        // Aquí se implementaría la lógica para extraer la pista de audio y guardarla temporalmente en la caché.
        // Podrías utilizar OkHttp o ExoPlayer para gestionar el streaming y almacenamiento en caché.
        CoroutineScope(Dispatchers.IO).launch {
            // Simulación de descarga con retardo
            delay(2000)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Descarga completada (cache temporal)", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
