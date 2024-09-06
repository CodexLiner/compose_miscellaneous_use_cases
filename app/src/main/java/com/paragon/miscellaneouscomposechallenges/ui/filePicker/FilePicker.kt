package com.paragon.miscellaneouscomposechallenges.ui.filePicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toDrawable
import coil.compose.AsyncImage

@Composable
fun FilePicker() {
    val result = remember { mutableStateOf<List<Uri?>?>(null) }
    var menuExpanded by remember { mutableStateOf(false) }
    var fileTypeState by remember { mutableStateOf(Pair(0, "Select File Type")) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        result.value = listOf(it)
    }
    val imageLauncherMultiple = rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {
        result.value = it
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier.weight(0.8f) , verticalArrangement = Arrangement.Top) {
            itemsIndexed(result.value.orEmpty()) { _, uri ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 10.dp)
                    .height(400.dp)) {
                    AsyncImage(
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                        model = if (fileTypeState.first == 3 || fileTypeState.first == 4 ) getVideoFirstFrame(LocalContext.current , uri!!) else uri,
                        contentDescription = null,
                    )
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(onClick = {menuExpanded = true }) {
                Text(text = fileTypeState.second)
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = {
                        menuExpanded = false
                    }
                ) {
                    DropdownMenuItem(onClick = { menuExpanded = false ; fileTypeState = Pair(1 , "Image Single")   } , text = { Text("Image Single")})
                    DropdownMenuItem(onClick = { menuExpanded = false ; fileTypeState = Pair(2 , "Image Multiple")   } , text = { Text("Image Multiple")})
                    DropdownMenuItem(onClick = { menuExpanded = false ; fileTypeState = Pair(3 , "Video Single")   } , text = { Text("Video Single")})
                    DropdownMenuItem(onClick = { menuExpanded = false ; fileTypeState = Pair(4 , "Video Multiple")   } , text = { Text("Video Multiple")})
                }
            }
            Button(onClick = {
                when (fileTypeState.first) {
                    1 -> launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    2 -> imageLauncherMultiple.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
                    3 -> launcher.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly))
                    4 -> imageLauncherMultiple.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly))
                }
            }) {
                Text("Open File Picker")
            }
        }
    }
}


@SuppressLint("Recycle")
fun getVideoFirstFrame(context: Context, videoUri: Uri): Bitmap? {
    val retriever = MediaMetadataRetriever()
    return try {
        val fileDescriptor = context.contentResolver.openFileDescriptor(videoUri, "r")?.fileDescriptor
        retriever.setDataSource(fileDescriptor)
        retriever.getFrameAtTime(0)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        retriever.release()
    }
}