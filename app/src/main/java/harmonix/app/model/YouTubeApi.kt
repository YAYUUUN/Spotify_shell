package harmonix.app.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApi {
    @GET("search")
    suspend fun searchVideos(
        @Query("part") part: String,
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("type") type: String,
        @Query("maxResults") maxResults: Int
    ): Response<SearchResponse>
}
