package com.example.login.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.login.viewmodel.SignUpViewModel


@Composable
fun SingUp(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        ScaffoldWithTopBar(navController)
    }
}

@Composable
fun ScaffoldWithTopBar(
    navController: NavController
) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    var passwordVisibility by remember { mutableStateOf(false) }

    var passwordDidNotMatch by remember { mutableStateOf(false) }

    var userExistCheck by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.ic_baseline_visibility_24)
    else
        painterResource(id = R.drawable.ic_baseline_visibility_off_24)
    

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = stringResource(id = R.string.sign_up),
                showBackIcon = true)
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //val context = LocalContext.current
                Text(
                    text = stringResource(id = R.string.sign_up),
                    fontSize = 30.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.first_name)) },
                    placeholder = { Text(text = stringResource(id = R.string.first_name)) },
                    value = firstName,
                    onValueChange = { firstName = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.last_name)) },
                    placeholder = { Text(text = stringResource(id = R.string.last_name)) },
                    value = lastName,
                    onValueChange = { lastName = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.username)) },
                    placeholder = { Text(text = stringResource(id = R.string.username)) },
                    value = userName,
                    onValueChange = { userName = it })

                if(userExistCheck){
                    Text(text = stringResource(id = R.string.user_exist),
                        color = colorResource(id = R.color.red)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.email)) },
                    placeholder = { Text(text = stringResource(id = R.string.email)) },
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.phone)) },
                    placeholder = { Text(text = stringResource(id = R.string.phone)) },
                    value = phone,
                    onValueChange = { phone = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.password)) },
                    placeholder = { Text(text = stringResource(id = R.string.password)) },
                    value = password,
                    onValueChange = { password = it },
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
                    else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    label = { Text(text = stringResource(id = R.string.confirm_password)) },
                    placeholder = { Text(text = stringResource(id = R.string.confirm_password)) },
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
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
                    else PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(20.dp))

                if(passwordDidNotMatch){
                    Text(text = stringResource(id = R.string.password_does_not_match),
                        color = colorResource(id = R.color.red)
                    )
                } else {
                    Text(text = stringResource(id = R.string.something_went_wrong),
                        color = colorResource(id = R.color.red)
                    )
                }

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)){
                    var singUp by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Button(
                        onClick = {
                            singUp = true
                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        if (singUp) {
                            val userInfo: LoginViewModel = hiltViewModel()
                            userInfo.getByUserPassword(userName)

                            val userData = userInfo.user.observeAsState()
                            val userExist = userData.value?.userName

                            singUp =  if(userExist != null){
                                userExistCheck = true
                                false
                            }
                            else if(password == confirmPassword) {
                                val signUpViewModel: SignUpViewModel = hiltViewModel()

                                val sign = SignUpData(
                                    firstName = firstName,
                                    lastName = lastName,
                                    userName = userName,
                                    email = email,
                                    phone = phone,
                                    password = password,
                                    confirmPassword = confirmPassword
                                )
                                signUpViewModel.addProfile(sign)
                                navController.navigate(Routes.Login.route)
                                false
                            } else {
                                passwordDidNotMatch = true
                                false
                            }
                        }

                        Text(text = stringResource(id = R.string.sign_up))
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun SignUpPreview(){
    SingUp(navController= rememberNavController())
}

@Preview(showSystemUi = true)
@Composable
fun ScaffoldWithTopBarPreview(){
    ScaffoldWithTopBar(navController = rememberNavController())
}