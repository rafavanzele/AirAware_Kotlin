package br.com.fiap.airaware.data.remote.model

data class AirPollutionResponse(
    val list: List<AirPollutionItem>
)

data class AirPollutionItem(
    val main: AirQualityMain,
    val components: AirComponents,
    val dt: Long
)

data class AirQualityMain(
    val aqi: Int
)

data class AirComponents(
    val co: Double,
    val no: Double,
    val no2: Double,
    val o3: Double,
    val so2: Double,
    val pm2_5: Double,
    val pm10: Double,
    val nh3: Double
)

