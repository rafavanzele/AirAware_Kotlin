package br.com.fiap.airaware.data.remote.model

data class GeocodingResponseItem(

    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String?

)