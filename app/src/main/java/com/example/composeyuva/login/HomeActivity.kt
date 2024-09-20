package com.example.composeyuva.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeyuva.ui.theme.ComposeYuvaTheme
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.composeyuva.R
import com.example.composeyuva.login.ui.theme.model.Post
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeYuvaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeUI(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun HomeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        // Profile Header
        ProfileHeader()

        // Story Highlights
        StoryHighlightsSection()

        // Story post category
        StoryProfileCategorySection()

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


        PostSection(posts = samplePosts)
    }
}
//
//@Composable
//fun HomeUI(modifier: Modifier = Modifier) {
//    // Create a ScrollState to manage scrolling
//    LazyColumn(
//        modifier = modifier
//            .fillMaxSize()
//            .padding(10.dp)
//    ) {
//        // Add the Profile Header
//        item {
//            ProfileHeader()
//        }
//
//        // Add the Story Highlights Section
//        item {
//            StoryHighlightsSection()
//        }
//
//        // Add the Story Profile Category Section
//        item {
//            StoryProfileCategorySection()
//        }
//
//        // Add the Posts Section
//        val samplePosts = listOf(
//            Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
//            Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
//            Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
//            Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
//            Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
//            Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
//            Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
//            Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
//            Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
//            Post(R.drawable.ic_nature, "Post 1", "This is a description of post 1."),
//            Post(R.drawable.ic_nature, "Post 2", "This is a description of post 2."),
//            Post(R.drawable.ic_nature, "Post 3", "This is a description of post 3."),
//        )
//        items(samplePosts) { post ->
//            PostItem(post)
//        }
//    }
//}



@Composable
fun ProfileHeader() {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // Make Row take the full width
            .padding(8.dp)  // Add padding to the Row
    ) {
        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f) // This makes the Column take up remaining space
                .padding(start = 2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "developer.by.passion", // Replace with dynamic name
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                // Down Arrow Icon using built-in icon
                Icon(
                    imageVector = Icons.Default.ArrowDropDown, // Built-in arrow icon
                    contentDescription = "Down Arrow",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp) // Size of the down arrow icon
                        .padding(start = 4.dp) // Padding to the left of the arrow
                )
            }
        }

        // Row for icons
        IconButton(onClick = { /* Handle notification click */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                contentDescription = "Notifications",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        IconButton(onClick = { /* Handle notification click */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                contentDescription = "Notifications",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }

        IconButton(onClick = { /* Handle notification click */ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                contentDescription = "Notifications",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }






    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // Make Row take the full width
            .padding(8.dp)  // Add padding to the Row
    ) {
        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 0.dp)
        ) {
            Box(
                modifier =
                Modifier.size(80.dp)
                    .clip(CircleShape)// Set the size of the Box to the size of the Image
            ) {
                // Background Image
                Image(
                    painter = painterResource(id = R.drawable.ic_nature), // Replace with your actual image resource
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .matchParentSize() // Fill the size of the Box
                        .clip(CircleShape), // Clip the image into a circular shape
                    contentScale = ContentScale.Crop // Ensure the image fills the circle properly
                )

                // Icon on top of the Image
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_circle_24), // Replace with your actual icon resource
                    contentDescription = "Notification Icon",
                    tint = Color.Cyan, // Set the color of the icon
                    modifier = Modifier
                        .align(Alignment.BottomEnd) // Position the icon in the bottom right corner
                        .size(30.dp) // Set the size of the icon
                        .padding(4.dp) // Add padding around the icon
                )
            }
        }



        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "108", // Replace with dynamic name
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "posts", // Replace with dynamic location
                fontSize = 10.sp,
                color = Color.Gray
            )
        }

        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "760", // Replace with dynamic name
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "followers", // Replace with dynamic location
                fontSize = 10.sp,
                color = Color.Gray
            )
        }

        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "812", // Replace with dynamic name
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "following", // Replace with dynamic location
                fontSize = 10.sp,
                color = Color.Gray
            )
        }


    }

    // Name and Location
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
//                horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sam malik", // Replace with dynamic name
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "@developer.by.passion", // Replace with dynamic location
            fontSize = 10.sp,
            color = Color.Gray
        )
        Text(
            text = "Battle with own curiosity", // Replace with dynamic location
            fontSize = 12.sp,
            color = Color.Black
        )

        Text(
            text = "MCA-BCA Ex- Hamdardian", // Replace with dynamic location
            fontSize = 12.sp,
            color = Color.Black
        )

        Text(
            text = "Sr. Android Developer", // Replace with dynamic location
            fontSize = 12.sp,
            color = Color.Black
        )
    }











}


@Composable
fun StoryHighlightsSection() {
    // Sample list of story highlights
    val storyHighlights = listOf(
        "Story 1", "Story 2", "Story 3", "Story 4", "Story 5"
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Spacing between items
    ) {
        items(storyHighlights) { story ->
            StoryHighlightItem(story)
        }
    }
}

@Composable
fun StoryHighlightItem(storyTitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        // Story Highlight Image
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .clickable { /* Handle story click */ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_nature), // Replace with your actual image resource
                contentDescription = storyTitle,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
        }

        // Story Highlight Title
        Text(
            text = storyTitle,
            fontSize = 10.sp,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun StoryProfileCategorySection(){


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth() // Make Row take the full width
            .padding(8.dp)  // Add padding to the Row
    ) {

        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row for icons
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                    contentDescription = "Notifications",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row for icons
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                    contentDescription = "Notifications",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Name and Location
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Row for icons
            IconButton(onClick = { /* Handle notification click */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notifications), // Replace with your actual icon resource
                    contentDescription = "Notifications",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }
        }


    }













}

//
@Composable
fun PostSection(posts: List<Post>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3), // Grid with 2 columns
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp), // Adds padding to the grid
        contentPadding = PaddingValues(5.dp), // Padding around the grid
        verticalArrangement = Arrangement.spacedBy(5.dp), // Spacing between rows
        horizontalArrangement = Arrangement.spacedBy(5.dp) // Spacing between columns
    ) {
        items(posts) { post ->
            PostItem(post)
        }
    }
}



@Composable
fun PostItem(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(0.dp)) // Adds border to the post
            .clip(RoundedCornerShape(0.dp))
    ) {
        Image(
            painter = painterResource(id = post.imageResourceId),
            contentDescription = post.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp) // Adjust the height as needed
                .clip(RoundedCornerShape(0.dp)), // Clip image with rounded corners
            contentScale = ContentScale.Crop
        )
//        Text(
//            text = post.title,
//            fontSize = 16.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(8.dp)
//        )
//        Text(
//            text = post.description,
//            fontSize = 14.sp,
//            color = Color.Gray,
//            modifier = Modifier.padding(horizontal = 8.dp)
//        )
    }
}








@Preview(showBackground = true)
@Composable
fun HomeUIScreenPreviewUI() {
    ComposeYuvaTheme {
        HomeUI()
    }
}



