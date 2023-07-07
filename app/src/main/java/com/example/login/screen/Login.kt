package com.example.login.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import com.example.login.ui.Routes
import com.example.login.ui.theme.Purple700
import com.example.login.viewmodel.LoginViewModel

private const val TAG = "Login"

@Composable
fun LoginPage(
    navController: NavController
){
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.sign_up_here)) ,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }

    Column(
        modifier = Modifier
            .padding(5.dp)
            .height(3500.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var username by rememberSaveable { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }




        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.ic_baseline_visibility_24)
        else
            painterResource(id = R.drawable.ic_baseline_visibility_off_24)

        Text(
            text = stringResource(id = R.string.login),
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.username)) },
            placeholder = { Text(text = stringResource(id = R.string.username)) },
            value = username,
            onValueChange = { username = it})

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.password)) },
            placeholder = { Text(text = stringResource(id = R.string.password)) },
            value = password,
            trailingIcon = {

                val description = if (passwordVisibility) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)

                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = description
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
            var buttonClick by rememberSaveable {
                mutableStateOf(false)
            }


            Button(
                onClick = { buttonClick = true},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {

                if(buttonClick) {
                    val loginUpViewModel: LoginViewModel = hiltViewModel()
                     loginUpViewModel.getByUserPassword(username)
                    val userdata = loginUpViewModel.user.observeAsState()
                    Log.d(TAG, "LoginPage:${userdata.value?.password}")
                    Log.d(TAG, "LoginPage:$password")
                    buttonClick = if (userdata.value?.password == password){
                        navController.navigate(Routes.MainPage.route)
                        false
                    } else {
                        Toast.makeText(context, "Wrong Username and Password", Toast.LENGTH_SHORT).show()
                        false
                    }
                }
                Text(text = stringResource(id = R.string.login))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString(stringResource(id = R.string.forgot_password_q)),
            onClick = { navController.navigate(Routes.ForgotPassword.route)},
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Color.Red
            )
        )
    }
}

@Preview
@Composable
fun LoginPagePreview(){
    LoginPage(navController = rememberNavController())
}