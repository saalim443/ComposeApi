package com.example.composeyuva.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeyuva.R
import com.example.composeyuva.ui.theme.ComposeYuvaTheme

class SearchAddressActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeYuvaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchAddressUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SearchAddressUI(modifier: Modifier = Modifier) {


    // Retrieve the current context for navigation
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Pick Address") },
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sample_image), // Replace with your drawable resource name
                    contentDescription = "Email Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Password Field with Visibility Toggle
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Drop Address") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sample_image), // Replace with your drawable resource name
                    contentDescription = "Password Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(2.dp))

        // Row for Horizontal Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp) // Space between buttons
        ) {
            // Button 1
            Button(
                onClick = { /* Handle action */ },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.weight(1f) ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,   // Set background color
                    contentColor = Color.White // Set text color to white
                )// Equal weight for half width
            ) {
                Text("Add More Drop", fontSize = 14.sp)
            }

            // Button 2
            Button(
                onClick = {  context.startActivity(Intent(context, CategoryActivity::class.java)) },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,   // Set background color
                    contentColor = Color.White // Set text color to white
                )

            // Equal weight for half width
            ) {
                Text("Choose a ride", fontSize = 14.sp)
            }
        }

        SearchAddressPostList()

    }



}

@Composable
fun SearchAddressPostList() {
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
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 2",
            "Post 3",
            "Post 4",
            "Post 4",
            "Post 4",
            "Post 4",
            "Post 4",
            "Post 4",
            "Post 5"
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(posts) { post ->
            SearchAddressPostItem(postTitle = post)
        }
    }
}



@Composable
fun SearchAddressPostItem(postTitle: String) {
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
fun SearchAddressScreenPreviewUI() {
    ComposeYuvaTheme {
        SearchAddressUI()
    }
}
