package com.example.composeyuva.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeyuva.login.ui.theme.RoundedTopShape
import com.example.composeyuva.ui.theme.ComposeYuvaTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*



class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeYuvaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DashUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashUI(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hasLocationPermission by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var location by remember { mutableStateOf("") }

    // Permission Request
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasLocationPermission = isGranted
        }
    )

    // Check for Location Permission
    LaunchedEffect(Unit) {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            hasLocationPermission = true
        } else {
            showPermissionDialog = true
        }
    }

    // Show permission dialog if needed
    if (showPermissionDialog) {
        LaunchedEffect(Unit) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            showPermissionDialog = false // Avoid re-launching
        }
    }

    // Default location (e.g., New York City)
    val defaultLocation = LatLng(40.7128, -74.0060)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 12f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasLocationPermission) {
            // Google Map Composable
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(myLocationButtonEnabled = true),
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                // Marker at current location
                Marker(
                    state = MarkerState(position = defaultLocation),
                    title = "Current Location",
                    snippet = "This is your current location"
                )
            }
        } else {
            Text(
                text = "Location permission required",
                modifier = Modifier.align(Alignment.Center),
                color = Color.Red
            )
        }

        // Card with TextField at the bottom center
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp),
            shape = RoundedTopShape(50.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Enter Location",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Type your location") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White, // Background color of TextField
//                        focusedIndicatorColor = Color.Blue, // Color of border when focused
//                        unfocusedIndicatorColor = Color.Gray, // Color of border when not focused
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedLabelColor = Color.Blue, // Color of label when focused
                        unfocusedLabelColor = Color.Gray, // Color of label when not focused
                    ),
                    shape = RoundedCornerShape(12.dp) // Change to your desired corner radius
                )


                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(
                    onClick = {
                        // Navigate to another screen or perform a login action
                        context.startActivity(Intent(context, SearchAddressActivity::class.java))
                              },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,   // Set background color
                        contentColor = Color.White // Set text color to white
                    )
                ) {
                    Text("Select Drop Location", fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashScreenPreviewUI() {
    ComposeYuvaTheme {
        DashUI()
    }
}
