package br.com.fiap.airaware.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.airaware.data.model.AirQualityData
import br.com.fiap.airaware.data.repository.AirQualityRepository
import kotlinx.coroutines.launch

class AirQualityViewModel : ViewModel() {

    private val repository = AirQualityRepository()

    var airQualityData by mutableStateOf<AirQualityData?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun searchCity(city: String) {

        viewModelScope.launch {

            try {

                isLoading = true
                errorMessage = null

                airQualityData = repository.getAirQualityByCity(city)

            } catch (e: Exception) {

                errorMessage = e.message

            } finally {

                isLoading = false

            }

        }

    }
}