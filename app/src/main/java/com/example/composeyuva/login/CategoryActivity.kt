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
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeyuva.R
import com.example.composeyuva.login.ui.theme.ComposeYuvaTheme
import com.example.composeyuva.login.ui.theme.RoundedTopShape
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class CategoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeYuvaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CategoryUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryUI(modifier: Modifier = Modifier) {
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

    Column(modifier = Modifier.fillMaxSize()) {
        if (hasLocationPermission) {
            // Google Map Composable takes up half the screen
            GoogleMap(
                modifier = Modifier
                    .weight(1f) // Take up half of the screen
                    .fillMaxWidth(),
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
                modifier = Modifier
                    .weight(1f) // Take up half of the screen
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = Color.Red
            )
        }

//        // Card Composable takes up the other half of the screen
//        Card(
//            modifier = Modifier
//                .weight(1f) // Take up half of the screen
//                .fillMaxWidth()
//                .padding(0.dp),
//            shape = RoundedCornerShape(0.dp),
//            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
//        ) {
//            // List of Posts
//            PostListCategory()
//        }

        Column(
            modifier = Modifier
                .weight(1f) // Take up half of the screen
                .fillMaxWidth()
                .padding(0.dp)
        ) {
            // List of Posts
            PostListCategory()

            // Add more composables here as needed
            // Example: Text("Another Item")
            // Button(onClick = { /* Do something */ }) { Text("Click Me") }
        }

    }
}

@Composable
fun PostListCategory() {
    // Mock data for posts
    val posts = remember {
        listOf(
            "Post 1",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",

        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(posts) { post ->
            PostListCategoryPostItem(postTitle = post)
        }
    }
}



@Composable
fun PostListCategoryPostItem(postTitle: String) {
    val context = LocalContext.current
    // State to manage click effect or any other logic (optional)
    var isClicked by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .clickable {
                // Handle click event
                isClicked = !isClicked
                context.startActivity(Intent(context, ConfirmPickupActivity::class.java))
                // You can add more logic here as needed
            },
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween, // Adjust arrangement to space between items
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left side: Profile Picture and Name
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile Picture
                    Image(
                        painter = painterResource(id = R.drawable.ic_sample_image), // Replace with your actual image resource
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(end = 0.dp)
                            .clip(CircleShape), // Clip the image into a circular shape
                        contentScale = ContentScale.Crop // Ensure the image fills the circle properly
                    )

                    // Name and Location
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = "John Doe", // Replace with dynamic name
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "New York, USA", // Replace with dynamic location
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                    }
                }

                // Right side: Notification Icon
                IconButton(
                    onClick = { /* Handle notification click */ },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                        contentDescription = "Notifications",
                        tint = Color.Red,  // Changed from Color.Gray to Color.Green
                        modifier = Modifier.size(24.dp)
                    )
                }




            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoryUIScreenPreviewUI() {
   ComposeYuvaTheme {
        CategoryUI()
    }



//   {
//       androidx.compose.foundation.layout.Column(
//           modifier = androidx.compose.ui.Modifier.padding(16.dp),
//           horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
//       ) {
//           androidx.compose.material3.Text(
//               text = "Information Card",
//               fontSize = 20.sp,
//               fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
//           )
//           androidx.compose.foundation.layout.Spacer(
//               modifier = androidx.compose.ui.Modifier.height(
//                   16.dp
//               )
//           )
//           androidx.compose.material3.Text(text = "This is some content inside the card.")
//           androidx.compose.foundation.layout.Spacer(
//               modifier = androidx.compose.ui.Modifier.height(
//                   16.dp
//               )
//           )
//           androidx.compose.material3.Button(onClick = { /* Handle click */ }) {
//               androidx.compose.material3.Text(text = "Click Me")
//           }
//       }
//   }
}