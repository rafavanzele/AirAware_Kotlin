package br.com.fiap.airaware.data.repository

import br.com.fiap.airaware.data.model.AirQualityData
import br.com.fiap.airaware.data.remote.ApiConfig
import br.com.fiap.airaware.data.remote.RetrofitInstance

class AirQualityRepository {

    suspend fun getAirQualityByCity(city: String): AirQualityData {

        val geocodingResult = RetrofitInstance.api.getCoordinatesByCity(
            city = city,
            apiKey = ApiConfig.API_KEY
        )

        if (geocodingResult.isEmpty()) {
            throw Exception("Cidade não encontrada")
        }

        val location = geocodingResult.first()

        val airResponse = RetrofitInstance.api.getAirPollution(
            lat = location.lat,
            lon = location.lon,
            apiKey = ApiConfig.API_KEY
        )

        val air = airResponse.list.first()

        return AirQualityData(
            city = location.name,
            aqi = air.main.aqi,
            co = air.components.co,
            no = air.components.no,
            no2 = air.components.no2,
            o3 = air.components.o3,
            so2 = air.components.so2,
            pm25 = air.components.pm2_5,
            pm10 = air.components.pm10,
            nh3 = air.components.nh3
        )
    }
}