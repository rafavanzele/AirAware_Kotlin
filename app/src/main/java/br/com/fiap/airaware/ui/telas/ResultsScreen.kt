package br.com.fiap.airaware.ui.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.airaware.data.model.AirQualityData
import br.com.fiap.airaware.ui.theme.AirAwareTheme
import br.com.fiap.airaware.ui.viewmodel.AirQualityViewModel
import androidx.activity.compose.BackHandler

@Composable
fun ResultsScreen(navController: NavHostController, viewModel: AirQualityViewModel) {

    val airData = viewModel.airQualityData

    BackHandler {
        viewModel.clearAirQualityData()
        navController.popBackStack()
    }

    if (airData == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Nenhum dado encontrado.",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        //.padding(24.dp),

    ) {

        SearchTopBar(
            onBackClick = {
                viewModel.clearAirQualityData()
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ResultsTitle()

            Spacer(modifier = Modifier.height(24.dp))

            AirQualitySection(data = airData, navController = navController)

        }
    }
}

fun getAirQualityStatus(aqi: Int): String {
    return when (aqi) {
        1 -> "Boa"
        2 -> "Razoável"
        3 -> "Moderada"
        4 -> "Ruim"
        5 -> "Muito Ruim"
        else -> "Desconhecida"
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun ResultsScreenPreview() {
//    AirAwareTheme() {
//        ResultsScreen(NavHostController(LocalContext.current))
//    }
//}


//Results header
@Composable
fun ResultsHeader(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "voltar",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview()
@Composable
private fun ResultsHeaderPreview() {
    AirAwareTheme() {
        ResultsHeader(onBackClick = {})
    }
}

//Results titulo
@Composable
fun ResultsTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Qualidade do Ar",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )
    }
}

@Preview
@Composable
private fun ResultsTitlePreview() {
    AirAwareTheme() {
        ResultsTitle()
    }
}

//Results components
@Composable
fun AirQualitySection(
    data: AirQualityData,
    navController: NavHostController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CityName(data.city)

        Spacer(modifier = Modifier.height(24.dp))

        AirQualityCard(aqi = data.aqi, status = getAirQualityStatus(data.aqi))

        Spacer(modifier = Modifier.height(24.dp))

        ResultsDetailsButton(navController)

        Spacer(modifier = Modifier.height(16.dp))

        ResultsTipsButton(navController)

    }

}

@Composable
fun CityName(city: String) {

    Text(
        text = city,
        style = MaterialTheme.typography.headlineLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = TextAlign.Center
    )

}

@Composable
fun AirQualityCard(
    aqi: Int,
    status: String
) {

    val backgroundColor = when (aqi) {
        1 -> Color(0xFF4CAF50)
        2 -> Color(0xFFFFC107)
        3 -> Color(0xFFFF9800)
        4 -> Color(0xFFF44336)
        5 -> Color(0xFF8E24AA)
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = aqi.toString(),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = status.uppercase(),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            AirQualityIndicator(status)

        }

    }
}


//componentes indicador-cores
@Composable
fun AirQualityIndicator(status: String) {

    val indicatorColor = when (status.lowercase()) {
        "boa" -> Color(0xFF4CAF50)
        "moderada" -> Color(0xFFFFC107)
        "ruim" -> Color(0xFFF44336)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .size(24.dp)
            .background(
                color = indicatorColor,
                shape = CircleShape
            )
    )
}

@Preview
@Composable
private fun AirQualityIndicatorPreview() {
    AirAwareTheme() {
        AirQualityIndicator("Moderada")
    }
}


//BOTOES
@Composable
fun ResultsDetailsButton(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate("detailsScreen")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp)
    ) {
        Text(
            text = "VER DETALHES",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun ResultsDetailsButtonPreview() {
    AirAwareTheme() {
        ResultsDetailsButton(NavHostController(LocalContext.current))
    }
}

@Composable
fun ResultsTipsButton(navController: NavHostController) {
    Button(
        onClick = {
            navController.navigate("environmentalTipsScreen")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(50.dp),
        elevation = ButtonDefaults.buttonElevation(8.dp)
    ) {
        Text(
            text = "DICAS AMBIENTAIS",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
private fun ResultsTipsButtonPreview() {
    AirAwareTheme() {
        ResultsTipsButton(NavHostController(LocalContext.current))
    }
}





