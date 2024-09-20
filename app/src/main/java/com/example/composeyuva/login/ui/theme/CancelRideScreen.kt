package com.example.composeyuva.login.ui.theme

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class) // Opt-in for using ModalBottomSheet
@Composable
fun CancelRideScreen(context: Context) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    // Main content layout
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Cancel Ride Button
            Button(
                onClick = {
                    // Show bottom sheet when button is clicked
                    isBottomSheetVisible = true
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,   // Set background color
                    contentColor = Color.White // Set text color to white
                )
            ) {
                Text("Cancel Ride", fontSize = 14.sp)
            }

            // Modal Bottom Sheet
            if (isBottomSheetVisible) {
                ModalBottomSheet(
                    onDismissRequest = { isBottomSheetVisible = false },
                    sheetState = sheetState,
                    content = {
                        BottomSheetContent(onReasonSelected = { reason ->
                            // Handle reason selected here
                            Toast.makeText(context, "Reason: $reason", Toast.LENGTH_SHORT).show()
                            isBottomSheetVisible = false // Close the sheet after selecting a reason
                        })
                    }
                )
            }
        }
    }
}

@Composable
fun BottomSheetContent(onReasonSelected: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Select a reason to cancel", fontSize = 18.sp, modifier = Modifier.padding(bottom = 16.dp))
        val reasons = listOf("Driver not come", "Driver is drunk", "Driver rating is not good")

        // Display list of reasons
        reasons.forEach { reason ->
            Text(
                text = reason,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onReasonSelected(reason) }
                    .padding(vertical = 8.dp)
            )
        }
    }
}
