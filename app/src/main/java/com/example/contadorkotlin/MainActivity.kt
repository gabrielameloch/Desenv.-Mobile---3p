package com.example.bancoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BancoApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BancoApp() {
    var saldo by remember { mutableStateOf(0.0) }
    var inputValor by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Banco App") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
        ) {
            Text(text = "Saldo Atual: R$ $saldo", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = inputValor,
                onValueChange = { inputValor = it }, // Corrigido para aceitar entrada do usuário
                label = { Text("Digite o valor") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    val valor = inputValor.toDoubleOrNull() ?: 0.0
                    if (valor > 0) saldo += valor
                    inputValor = ""
                }) {
                    Text("Depositar")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = {
                    val valor = inputValor.toDoubleOrNull() ?: 0.0
                    if (valor > 0 && valor <= saldo) saldo -= valor
                    inputValor = ""
                }) {
                    Text("Sacar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { saldo = 0.0 }) {
                Text("Zerar Conta")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BancoAppPreview() {
    BancoApp()
}
