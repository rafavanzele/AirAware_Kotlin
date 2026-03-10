package br.com.fiap.airaware.ui.telas

import br.com.fiap.airaware.data.model.AirQualityData

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.airaware.ui.theme.AirAwareTheme
import br.com.fiap.airaware.ui.telas.SearchTopBar

@Composable
fun ResultsScreen() {

    val airData = AirQualityData(
        city = "São Paulo",
        aqi = 78,
        status = "Moderada",
        pm25 = 18.0,
        pm10 = 30.0,
        co = 0.8,
        o3 = 12.0
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            //.padding(24.dp),

    ) {

        SearchTopBar(
            onBackClick = {},
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

            AirQualitySection(data = airData)

        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ResultsScreenPreview() {
    AirAwareTheme() {
        ResultsScreen()
    }
}


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
    data: AirQualityData
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CityName(data.city)

        Spacer(modifier = Modifier.height(24.dp))

        AirQualityCard(data.aqi, status = data.status)

        Spacer(modifier = Modifier.height(24.dp))

        ResultsDetailsButton()

        Spacer(modifier = Modifier.height(16.dp))

        ResultsTipsButton()

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

    val backgroundColor = when {
        aqi <= 50 -> Color(0xFF4CAF50)     // Verde
        aqi <= 100 -> Color(0xFFFFC107)    // Amarelo
        aqi <= 150 -> Color(0xFFFF9800)    // Laranja
        else -> Color(0xFFF44336)          // Vermelho
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
fun ResultsDetailsButton() {
    Button(
        onClick = {},
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
        ResultsDetailsButton()
    }
}

@Composable
fun ResultsTipsButton() {
    Button(
        onClick = {},
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
        ResultsTipsButton()
    }
}





