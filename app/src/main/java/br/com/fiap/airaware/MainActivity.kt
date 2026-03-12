package br.com.fiap.airaware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.airaware.ui.telas.DetailsScreen
import br.com.fiap.airaware.ui.telas.EnvironmentalTipsScreen
import br.com.fiap.airaware.ui.telas.HomeScreen
import br.com.fiap.airaware.ui.telas.ResultsScreen
import br.com.fiap.airaware.ui.telas.SearchCityScreen
import br.com.fiap.airaware.ui.theme.AirAwareTheme
import br.com.fiap.airaware.ui.viewmodel.AirQualityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AirAwareTheme {

                val navController = rememberNavController()

                val airQualityViewModel: AirQualityViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    composable(route = "home") {
                        HomeScreen(navController)
                    }

                    composable(route = "searchScreen") {
                        SearchCityScreen(
                            navController = navController,
                            viewModel = airQualityViewModel
                        )
                    }

                    composable(route = "resultsScreen") {
                        ResultsScreen(
                            navController = navController,
                            viewModel = airQualityViewModel
                        )
                    }

                    composable(route = "detailsScreen") {
                        DetailsScreen(
                            navController = navController,
                            viewModel = airQualityViewModel
                        )
                    }

                    composable(route = "environmentalTipsScreen") {
                        EnvironmentalTipsScreen(
                            navController = navController,
                            viewModel = airQualityViewModel
                        )
                    }
                }
            }
        }
    }
}