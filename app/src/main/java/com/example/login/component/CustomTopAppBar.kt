package com.example.login.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CustomTopAppBar(
    navController: NavController,
    title: String,
    showBackIcon: Boolean
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = if(showBackIcon && navController.previousBackStackEntry != null){
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }

        } else {
            null
        }
    )
}