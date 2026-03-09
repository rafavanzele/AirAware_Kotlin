package br.com.fiap.airaware.ui.telas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.airaware.ui.theme.AirAwareTheme
import androidx.compose.material3.CenterAlignedTopAppBar


//SearchScreen
@Composable
fun SearchCityScreen() {

    Scaffold(
        topBar = {
            SearchTopBar(
                onBackClick = { }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(padding)
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(48.dp))

                SearchTitle()

                Spacer(modifier = Modifier.height(32.dp))

                SearchInputSection()

                Spacer(modifier = Modifier.height(64.dp))

                PopularCitiesList()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SearchCityScreenPreview() {
    AirAwareTheme() {
        SearchCityScreen()
    }
}


//Botao voltar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTopBar(onBackClick: () -> Unit) {

    IconButton(
        onClick = onBackClick,
        modifier = Modifier.padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Voltar",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchTopBarPreview() {
    AirAwareTheme() {
        SearchTopBar(onBackClick = {})
    }
}


//SearchScreenTitle
@Composable
fun SearchTitle() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Buscar cidade",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Qual cidade deseja consultar?",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun SearchTitlePreview() {
    AirAwareTheme() {
        SearchTitle()
    }
}


//Search input
@Composable
fun SearchInputSection() {
    Column() {

        var texto by remember { mutableStateOf("") }

        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            placeholder = {
                Text(
                    "Digite aqui",
                    color = MaterialTheme.colorScheme.secondary
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    println("Buscar clicado")
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "buscar"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(50.dp),
            elevation = ButtonDefaults.buttonElevation(8.dp)
        ) {
            Text(
                text = "CONSULTAR",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun SearchInputSectionPreview() {
    AirAwareTheme() {
        SearchInputSection()
    }
}


//Lista de cidades
@Composable
fun PopularCitiesList() {

    val cidades = listOf(
        "São Paulo",
        "Rio de Janeiro",
        "Curitiba",
        "Belo Horizonte",
        "Porto Alegre"
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Cidades populares",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {

            items(cidades) { cidade -> CityItem(cidade)

            }
        }
    }
}

@Preview
@Composable
private fun PopularCitiesListPreview() {
    AirAwareTheme() {
        PopularCitiesList()
    }
}

//City item
@Composable
fun CityItem(city: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Localização",
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = city,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
