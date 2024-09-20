package com.example.composeyuva.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeyuva.R
import com.example.composeyuva.login.ui.theme.ComposeYuvaTheme
import com.example.composeyuva.login.ui.theme.model.Post

class ProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(modifier = Modifier.padding(innerPadding))
                }

        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    // Posts Section
    val samplePosts = listOf(
        Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
        Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
        Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
        Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
        Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
        Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
        Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
        Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
        Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
        Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
        Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
        Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
    )
    PostSectionUi(posts = samplePosts)
}


@Composable
fun PostSectionUi(posts: List<Post>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Grid with 2 columns
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp), // Adds padding to the grid
        contentPadding = PaddingValues(5.dp), // Padding around the grid
        verticalArrangement = Arrangement.spacedBy(5.dp), // Spacing between rows
        horizontalArrangement = Arrangement.spacedBy(5.dp) // Spacing between columns
    ) {
        items(posts) { post ->
            PostItemUI(post)
        }
    }
}



@Composable
fun PostItemUI(post: Post) {
    // State to hold the current quantity
    val quantity = remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(0.dp)) // Adds border to the post
            .clip(RoundedCornerShape(0.dp))
            .clickable {

               // context.startActivity(Intent(context, ConfirmPickupActivity::class.java))
                // You can add more logic here as needed
            },
    ) {
        Image(
            painter = painterResource(id = post.imageResourceId),
            contentDescription = post.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp) // Adjust the height as needed
                .clip(RoundedCornerShape(0.dp)), // Clip image with rounded corners
            contentScale = ContentScale.Crop
        )
        Text(
            text = post.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = post.description,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        // Add to Cart Section with - and + buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (quantity.value > 1) quantity.value -= 1
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add), // Replace with your own "-" icon drawable
                        contentDescription = "Decrease quantity",
                        tint = Color.Black
                    )
                }
                Text(
                    text = quantity.value.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(
                    onClick = {
                        quantity.value += 1
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add), // Replace with your own "+" icon drawable
                        contentDescription = "Increase quantity",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeYuvaTheme {
        Greeting()
    }
}