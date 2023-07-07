package com.example.login.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.Routes


/*
   * Everything will go here in this MainPage.
   *
   *  1. signup or login page
   *  2. authentication
   *  3. push-up notifications
   *  4. digital wallet
   *  5. unique ID
   *  6. OTP verification
   *  7. chat functionality (Optional for My app)
   *  8. bill and invoice facility
   *  9. transaction history
   *  10. sending, and receiving money.
   *
   * */
@Composable
fun MainPage(navController: NavController){
    Column (
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var backButton by rememberSaveable{
            mutableStateOf(false)
        }
        val context = LocalContext.current

        Text(
            text = "Welcome",
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        )

        Button(onClick = { backButton = true },
            shape = RoundedCornerShape(50.dp)
        ) {
            backButton = if (backButton) {
                navController.navigate(Routes.Login.route)
                false
            } else {
                Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show()
                false
            }
            Text(text = "Back to Login")
        }
    }


}

@Preview
@Composable
fun MainPagePreview() {
    MainPage(navController = rememberNavController())
}