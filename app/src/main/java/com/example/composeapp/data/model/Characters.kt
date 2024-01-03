package com.example.composeapp.data.model


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Result>
) {
    data class Info(
        @SerializedName("count")
        val count: Int, // 826
        @SerializedName("next")
        val next: String, // https://rickandmortyapi.com/api/character?page=2
        @SerializedName("pages")
        val pages: Int, // 42
        @SerializedName("prev")
        val prev: Any? // null
    )

    data class Result(
        @SerializedName("created")
        val created: String, // 2017-11-04T18:48:46.250Z
        @SerializedName("episode")
        val episode: List<String>,
        @SerializedName("gender")
        val gender: String, // Male
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("image")
        val image: String, // https://rickandmortyapi.com/api/character/avatar/1.jpeg
        @SerializedName("location")
        val location: Location,
        @SerializedName("name")
        val name: String, // Rick Sanchez
        @SerializedName("origin")
        val origin: Origin,
        @SerializedName("species")
        val species: String, // Human
        @SerializedName("status")
        val status: String, // Alive
        @SerializedName("type")
        val type: String,
        @SerializedName("url")
        val url: String // https://rickandmortyapi.com/api/character/1
    ) {
        data class Location(
            @SerializedName("name")
            val name: String, // Citadel of Ricks
            @SerializedName("url")
            val url: String // https://rickandmortyapi.com/api/location/3
        )

        data class Origin(
            @SerializedName("name")
            val name: String, // Earth (C-137)
            @SerializedName("url")
            val url: String // https://rickandmortyapi.com/api/location/1
        )
    }
}