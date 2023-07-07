package com.example.login.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.login.R
import com.example.login.component.CustomTopAppBar
import com.example.login.model.database.SignUpData
import com.example.login.ui.Routes
import com.example.login.viewmodel.LoginViewModel
import com.example.login.viewmodel.RestPasswordViewModel

@Composable
fun ForgotPassword( navController: NavController){
    Box( modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarForgotPass(navController = navController)
    }
}

@Composable
fun ScaffoldWithTopBarForgotPass(navController: NavController){

    var username by rememberSaveable { mutableStateOf("") }
    var newPassword by rememberSaveable { mutableStateOf("") }
    var confirmNewPassword by rememberSaveable { mutableStateOf("") }
    var passwordDidNotMatch by remember { mutableStateOf(false) }
    var userDidNotFound by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.forgot_password),
                showBackIcon = true)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val context = LocalContext.current

                Text(
                    text = stringResource(id = R.string.reset_password),
                    fontSize = 30.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.username))},
                    placeholder = { Text(text = stringResource(id = R.string.username))},
                    value = username,
                    onValueChange = { username = it })

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.new_password)) },
                    placeholder = { Text(text = stringResource(id = R.string.new_password)) },
                    value = newPassword,
                    onValueChange = { newPassword = it })

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.confirm_new_password)) },
                    placeholder = { Text(text = stringResource(id = R.string.confirm_new_password)) },
                    value = confirmNewPassword,
                    onValueChange = { confirmNewPassword = it })

                Spacer(modifier = Modifier.height(20.dp))

                if (passwordDidNotMatch && userDidNotFound) {
                    Text(text = stringResource(id = R.string.user_and_password),
                        color = colorResource(id = R.color.red)
                    )
                }
                else if(passwordDidNotMatch){
                    Text(text = stringResource(id = R.string.password_does_not_match),
                        color = colorResource(id = R.color.red)
                    )
                } else if (userDidNotFound) {
                    Text(text = stringResource(id = R.string.user_did_not_found),
                        color = colorResource(id = R.color.red)
                    )
                } else {
                    Text(text = stringResource(id = R.string.something_went_wrong),
                        color = colorResource(id = R.color.red)
                    )
                }

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
                    var pssSubmit by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Button(
                        onClick = { pssSubmit = true },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                       if (pssSubmit){
                            if(newPassword == confirmNewPassword) {
                                val resetViewModel: RestPasswordViewModel = hiltViewModel()

                                val userInfo: LoginViewModel = hiltViewModel()
                                userInfo.getByUserPassword(username)

                                val userData = userInfo.user.observeAsState()


                                val resetPss = userData.value?.let { it ->
                                    SignUpData(
                                        it.userId,
                                        it.firstName,
                                        it.lastName,
                                        it.userName,
                                        it.email,
                                        it.phone,
                                        password = newPassword,
                                        confirmPassword = confirmNewPassword
                                    )
                                }


                                if (resetPss != null) {
                                    resetViewModel.updateUser(resetPss)
                                    navController.navigate(Routes.Login.route)
                                } else {
                                    userDidNotFound = true
                                }

                                pssSubmit = false
                            } else {
                                passwordDidNotMatch = true
                            }
                        }
                        Text(text = stringResource(id = R.string.reset_password))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun ForgotPasswordPreview(){
    ForgotPassword(navController = rememberNavController())
}