package br.com.fiap.airaware.data.remote

import br.com.fiap.airaware.data.remote.model.AirPollutionResponse
import br.com.fiap.airaware.data.remote.model.GeocodingResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {

    @GET("geo/1.0/direct")
    suspend fun getCoordinatesByCity(
        @Query("q") city: String,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String
    ): List<GeocodingResponseItem>

    @GET("data/2.5/air_pollution")
    suspend fun getAirPollution(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String
    ): AirPollutionResponse
}