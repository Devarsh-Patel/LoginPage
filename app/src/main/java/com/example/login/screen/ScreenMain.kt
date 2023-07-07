package com.example.login.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.login.ui.Routes


@Composable
fun ScreenMain(
    navController: NavHostController
){
    NavHost(navController = navController,
        startDestination = Routes.Login.route
    ) {
        composable(Routes.Login.route){
            LoginPage(navController = navController)
        }

        composable(Routes.SignUp.route) {
            SingUp(navController = navController)
        }

        composable(Routes.ForgotPassword.route){
            ForgotPassword(navController = navController)
        }

        composable(Routes.MainPage.route){
            MainPage(navController = navController)
        }
    }
}
