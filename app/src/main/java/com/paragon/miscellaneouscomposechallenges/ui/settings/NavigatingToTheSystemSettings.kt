package com.paragon.miscellaneouscomposechallenges.ui.settings

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Preview(showSystemUi = true)
@Composable
fun NavigatingToTheSystemSettings() {
    var textFieldState by remember {
        mutableStateOf("here response will be shown")
    }
    var menuExpanded by remember { mutableStateOf(false) }
    var settingType by remember { mutableStateOf(Pair(0, "Select Setting Type")) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) {
        textFieldState = it.toString()
    }
    val launcherSingleContact = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        textFieldState = it.toString()
    }
    val launcherSetting = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        textFieldState = it.toString()
    }
    val takePicture = rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenDocumentTree()) {
        textFieldState = it.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp).safeDrawingPadding(),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            readOnly = true,
            value = textFieldState,
            onValueChange = { textFieldState = it }
        )
        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    launcher.launch(
                        arrayOf(
                            "android.permission.READ_EXTERNAL_STORAGE",
                            "android.permission.WRITE_EXTERNAL_STORAGE",
                            "android.permission.CAMERA",
                            "android.permission.READ_CONTACTS"
                        )
                    )
                }
            ) {
                Text(text = "Request Multiple Permission")
            }
            Button(onClick = {launcherSingleContact.launch("android.permission.READ_CONTACTS")
            }) {
                Text(text = "Open Contact Permission")
            }
            Button(onClick = {
                takePicture.launch(Uri.parse("//Todo: you need to pass file path uri here where it will be saved temp."))
            }) {
                Text(text = "Take Picture")
            }
            OutlinedButton(onClick = { menuExpanded = true }) {
                Text(text = settingType.second)
                DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                    DropdownMenuItem(
                        onClick = {
                            menuExpanded = false; settingType = Pair(1, "ACTION_BLUETOOTH_SETTINGS")
                        }, text = { Text("ACTION_BLUETOOTH_SETTINGS") })
                    DropdownMenuItem(
                        onClick = {
                            menuExpanded = false; settingType = Pair(2, "ACTION_WIFI_SETTINGS")
                        }, text = { Text("ACTION_WIFI_SETTINGS") })
                    DropdownMenuItem(
                        onClick = {
                            menuExpanded = false; settingType = Pair(3, "ACTION_DISPLAY_SETTINGS")
                        }, text = { Text("ACTION_DISPLAY_SETTINGS") })
                    DropdownMenuItem(
                        onClick = {
                            menuExpanded = false; settingType =
                            Pair(4, "ACTION_MANAGE_OVERLAY_PERMISSION")
                        }, text = { Text("ACTION_MANAGE_OVERLAY_PERMISSION") })
                }
            }
            Button(onClick = {
                when (settingType.first) {
                    1 -> launcherSetting.launch(input = Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS))
                    2 -> launcherSetting.launch(input = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS))
                    3 -> launcherSetting.launch(input = Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS))
                    4 -> launcherSetting.launch(input = Intent(android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION))
                }
            }) {
                Text("Open ${settingType.second} Setting")
            }
        }
    }
}