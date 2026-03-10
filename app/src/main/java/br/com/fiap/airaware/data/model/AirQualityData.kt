package br.com.fiap.airaware.data.model

data class AirQualityData(
    val city: String,
    val aqi: Int,
    val status: String,
    val pm25: Double,
    val pm10: Double,
    val co: Double
)
