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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.airaware.ui.theme.AirAwareTheme
import br.com.fiap.airaware.ui.viewmodel.AirQualityViewModel

@Composable
fun DetailsScreen(navController: NavHostController, viewModel: AirQualityViewModel) {

    val airData = viewModel.airQualityData

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
    ) {
        SearchTopBar(
            onBackClick = {
                navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.TopStart)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailsTitle()

            Spacer(modifier = Modifier.height(24.dp))

            DetailsSubtitle(city = airData.city)

            Spacer(modifier = Modifier.height(32.dp))

            PollutantsCard(
                pm25 = airData.pm25,
                pm10 = airData.pm10,
                co = airData.co,
                o3 = airData.o3
            )

            Spacer(modifier = Modifier.height(48.dp))

            DetailsTipsButton(navController)
        }
    }

}

//@Preview(showSystemUi = true)
//@Composable
//private fun DetailsScreenPreview() {
//    AirAwareTheme() {
//        DetailsScreen(NavHostController(LocalContext.current))
//    }
//}


//Detail title
@Composable
fun DetailsTitle() {
    Text(
        text = "Detalhes do ar",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun DetailsTitlePreview() {
    AirAwareTheme() {
        DetailsTitle()
    }
}


//Details subtitle
@Composable
fun DetailsSubtitle(city: String) {
    Text(
        text = "Qualidade do ar em $city",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun DetailsSubtitlePreview() {
    AirAwareTheme() {
        DetailsSubtitle(city = "São Paulo")
    }
}

//poluentes card
@Composable
fun PollutantsCard(
    pm25: Double,
    pm10: Double,
    co: Double,
    o3: Double
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9D9D9))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PollutantItem(
                pollutant = "PM2.5",
                value = pm25.toString(),
                description = "partículas finas"
            )

            PollutantItem(
                pollutant = "PM10",
                value = pm10.toString(),
                description = "poeira e poluição"
            )

            PollutantItem(
                pollutant = "CO",
                value = co.toString(),
                description = "monóxido carbono"
            )

            PollutantItem(
                pollutant = "O3",
                value = o3.toString(),
                description = "ozônio atmosférico"
            )
        }
    }
}

@Preview
@Composable
private fun PollutantsCardPreview() {
    AirAwareTheme() {
        PollutantsCard(
            pm25 = 18.0,
            pm10 = 30.0,
            co = 0.8,
            o3 = 12.0
        )
    }
}

//poluentes itens
@Composable
fun PollutantItem(
    pollutant: String,
    value: String,
    description: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = pollutant,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(32.dp))

            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Preview
@Composable
private fun PollutantItemPreview() {
    AirAwareTheme() {
        PollutantItem(
            pollutant = "PM2.5",
            value = "18",
            description = "partículas finas"
        )
    }
}

//botao dicas
@Composable
fun DetailsTipsButton(navController: NavHostController) {
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

//@Preview
//@Composable
//private fun DetailsTipsButtonPreview() {
//    AirAwareTheme() {
//        DetailsTipsButton(NavHostController(LocalContext.current))
//    }
//}












