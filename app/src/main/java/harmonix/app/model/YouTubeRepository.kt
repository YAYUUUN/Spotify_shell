package harmonix.app.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YouTubeRepository {

    // TODO: your YOUTUBE API KEY
    private val apiKey = "your API key"
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(YouTubeApi::class.java)

    fun search(
        query: String,
        maxResults: Int = 10,
        onSuccess: (List<VideoItem>) -> Unit,
        onError: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Asegúrate de pasar 'apiKey' correctamente
                val response = api.searchVideos(
                    part = "snippet",
                    query = query,
                    apiKey = apiKey,  // Pasamos la clave de API correctamente
                    type = "video",
                    maxResults = maxResults // El valor de maxResults se pasa correctamente
                )

                // Verifica si la solicitud fue exitosa
                if (response.isSuccessful) {
                    val videos = response.body()?.items ?: emptyList()
                    // Llama a onSuccess en el hilo principal
                    CoroutineScope(Dispatchers.Main).launch {
                        onSuccess(videos)
                    }
                } else {
                    // Llama a onError en el hilo principal con el mensaje de error
                    CoroutineScope(Dispatchers.Main).launch {
                        onError("Error: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                // Llama a onError en el hilo principal con la excepción
                CoroutineScope(Dispatchers.Main).launch {
                    onError("Error: ${e.localizedMessage}")
                }
            }
        }
    }
}
