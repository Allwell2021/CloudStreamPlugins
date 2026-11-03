package com.example

import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*
import com.fasterxml.jackson.annotation.JsonProperty

class ExampleProvider : MainAPI() {
    // The base URL for the movie API
    override var mainUrl = "https://yts.mx"
    
    // The name that will show up in CloudStream
    override var name = "My Movie Plugin"
    
    // The types of content this provider supports
    override val supportedTypes = setOf(TvType.Movie)
    override var lang = "en"
    override val hasMainPage = true

    // This function loads the Home tab
    override suspend fun getMainPage(page: Int, request: MainPageRequest): HomePageResponse {
        val movies = app.get("$mainUrl/api/v2/list_movies.json?limit=20&page=$page")
            .parsedSafe<YtsResponse>()?.data?.movies?.map { movie ->
                newMovieSearchResponse(movie.title ?: "Unknown", "$mainUrl/movie/${movie.id}", TvType.Movie) {
                    this.posterUrl = movie.medium_cover_image
                }
            } ?: emptyList()

        return newHomePageResponse("Latest Movies", movies)
    }

    // This function searches for movies
    override suspend fun search(query: String): List<SearchResponse> {
        return app.get("$mainUrl/api/v2/list_movies.json?query_term=$query")
            .parsedSafe<YtsResponse>()?.data?.movies?.map { movie ->
                newMovieSearchResponse(movie.title ?: "Unknown", "$mainUrl/movie/${movie.id}", TvType.Movie) {
                    this.posterUrl = movie.medium_cover_image
                }
            } ?: emptyList()
    }
}

// These data classes tell the plugin how to read the JSON data from YTS
data class YtsResponse(
    @JsonProperty("data") val data: YtsData? = null
)

data class YtsData(
    @JsonProperty("movies") val movies: List<YtsMovie>? = null
)

data class YtsMovie(
    @JsonProperty("id") val id: Int? = null,
    @JsonProperty("title") val title: String? = null,
    @JsonProperty("medium_cover_image") val medium_cover_image: String? = null
)
