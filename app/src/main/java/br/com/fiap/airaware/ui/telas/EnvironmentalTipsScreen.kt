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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.airaware.ui.theme.AirAwareTheme

@Composable
fun EnvironmentalTipsScreen(navController: NavHostController) {

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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            EnvironmentalTipsTitle()

            Spacer(modifier = Modifier.height(24.dp))

            EnvironmentalTipsSubtitle()

            Spacer(modifier = Modifier.height(32.dp))

            TipsList()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun EnvironmentalTipsScreenPreview() {
    AirAwareTheme() {
        EnvironmentalTipsScreen(NavHostController(LocalContext.current))
    }
}


//title
@Composable
fun EnvironmentalTipsTitle() {
    Text(
        text = "Dicas ambientais",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun EnvironmentalTipsTitlePreview() {
    AirAwareTheme() {
        EnvironmentalTipsTitle()
    }
}

//subtitulo
@Composable
fun EnvironmentalTipsSubtitle() {
    Text(
        text = "Como melhorar a qualidade do ar",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun EnvironmentalTipsSubtitlePreview() {
    AirAwareTheme() {
        EnvironmentalTipsSubtitle()
    }
}

//Tips list
@Composable
fun TipsList() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        EnvironmentalTipCard(
            icon = Icons.Default.DirectionsBike,
            iconBackgroundColor = Color(0xFF5FA941),
            tipText = "Use transporte público, bicicleta ou caminhe mais"
        )

        EnvironmentalTipCard(
            icon = Icons.Default.Eco,
            iconBackgroundColor = Color(0xFF3FA7A3),
            tipText = "Reduza o uso de veículos e escolha energias renováveis"
        )

        EnvironmentalTipCard(
            icon = Icons.Default.LocalFireDepartment,
            iconBackgroundColor = Color(0xFFF4BE18),
            tipText = "Reduza o consumo de energia e evite queimadas"
        )

        EnvironmentalTipCard(
            icon = Icons.Default.Forest,
            iconBackgroundColor = Color(0xFF7CB342),
            tipText = "Plante árvores e apoie áreas verdes"
        )
    }
}

@Preview
@Composable
private fun TipsListPreview() {
    AirAwareTheme() {
        TipsList()
    }
}

//tips card
@Composable
fun EnvironmentalTipCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBackgroundColor: Color,
    tipText: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9D9D9))
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(iconBackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(38.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = tipText,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
private fun EnvironmentalTipCardPreview() {
    AirAwareTheme() {
        EnvironmentalTipCard(
            icon = Icons.Default.DirectionsBike,
            iconBackgroundColor = Color(0xFF5FA941),
            tipText = "Use transporte público, bicicleta ou caminhe mais"
        )
    }
}
















