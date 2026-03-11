package br.com.fiap.airaware.data.model

data class AirQualityData(
    val city: String,
    val aqi: Int,
    val co: Double,
    val no: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm25: Double,
    val pm10: Double,
    val nh3: Double
)
