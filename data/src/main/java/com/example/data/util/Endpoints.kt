package com.example.data.util

object Endpoints {

    const val CHARACTERS = "/v1/public/characters"
    const val COMICS = "/v1/public/comics"
    const val CREATORS = "/v1/public/creators"
    const val SERIES = "/v1/public/series"
    const val CHARACTER_DETAILS = "/v1/public/characters/{id}"
    const val COMIC_DETAILS = "/v1/public/comics/{id}"
    const val CREATOR_DETAILS = "/v1/public/creators/{id}"
    const val SERIES_DETAILS = "/v1/public/series/{id}"

    const val API_KEY =
        "?apikey=${ApiConstants.MARVEL_API_PUBLIC_KEY}&ts=${ApiConstants.MARVEL_API_TS}&hash=${ApiConstants.MARVEL_API_HASH}"

}
