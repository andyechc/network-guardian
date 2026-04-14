package com.andyechc.network_guardian

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andyechc.network_guard.NetworkResult
import com.andyechc.network_guard.safeApiCall
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkTestScreen() {
    val scope = rememberCoroutineScope()
    var resultText by remember { mutableStateOf("Ready to test") }
    var isLoading by remember { mutableStateOf(false) }
    
    val client = remember { HttpClient() }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Network Guardian Test") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = resultText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        scope.launch {
                            isLoading = true
                            resultText = "Testing Network Error..."
                            
                            // Target a non-existent URL to trigger a network error
                            val result = safeApiCall<String> {
                                client.get("https://this-url-does-not-exist-12345.com").bodyAsText()
                            }
                            
                            resultText = when (result) {
                                is NetworkResult.Success -> "Success: ${result.data}"
                                is NetworkResult.Error -> "Caught Error: ${result.message}"
                            }
                            isLoading = false
                        }
                    }
                ) {
                    Text("Trigger Network Error")
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = {
                        scope.launch {
                            isLoading = true
                            resultText = "Testing 404 Error..."
                            
                            // Target a URL that returns 404
                            val result = safeApiCall<HttpResponse> {
                                client.get("https://httpstat.us/404")
                            }
                            
                            resultText = when (result) {
                                is NetworkResult.Success -> "Unexpected Success: ${result.data.status}"
                                is NetworkResult.Error -> "Caught Error 2: ${result.message}"
                            }
                            isLoading = false
                        }
                    }
                ) {
                    Text("Trigger 404 Error")
                }
            }
        }
    }
}
