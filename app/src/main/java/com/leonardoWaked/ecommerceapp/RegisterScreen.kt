package com.leonardoWaked.ecommerceapp

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onClickBack: ()->Unit = {}, onSuccessfulRegister:()-> Unit = {}){


    val auth = Firebase.auth

    val activity = LocalView.current.context as Activity

    var inputName by remember { mutableStateOf("") }
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var inputPasswordConfirmation by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var passwordConfirmationError by remember { mutableStateOf("") }

    var registerError by remember { mutableStateOf("") }

     Scaffold (
         topBar = {
             TopAppBar(
                 title = {},
                 navigationIcon = {
                     IconButton(onClick = onClickBack) {
                         Icon(Icons.Default.ArrowBack, contentDescription = null)
                     }
                 }
             )
         }
     ){ innerPadding ->
         Column (modifier = Modifier.padding(innerPadding)
             .padding(horizontal = 32.dp)
             .imePadding()
             .verticalScroll(rememberScrollState()),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center) {

             Image(imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(100.dp))

             Spacer(modifier = Modifier.height(31.dp)
             )

             Text(
                 text = "Registrarse",
                 fontSize = 24.sp,
                 color = Color(0xFFFF9900),
                 fontWeight = FontWeight.Bold
             )

             Spacer(modifier = Modifier.height(30.dp))

             OutlinedTextField(
                 value = inputEmail,
                 onValueChange = {inputEmail = it},
                 modifier = Modifier.fillMaxWidth(),
                 leadingIcon = {
                     Icon(
                         imageVector = Icons.Default.Email,
                         contentDescription = "Email",
                         tint = Color(0xFFFF9900)
                     )
                 },
                 label = {
                     Text(text = "Correo Electronico")
                 },
                 shape = RoundedCornerShape(12.dp),
                 supportingText = {
                     if (emailError.isNotEmpty()){
                         Text(
                             text = emailError,
                             color = Color.Red
                         )
                     }
                 }
             )

             Spacer(modifier = Modifier.height(30.dp))

             OutlinedTextField(
                 value = inputName,
                 onValueChange = {inputName = it},
                 modifier = Modifier.fillMaxWidth(),
                 leadingIcon = {
                     Icon(
                         imageVector = Icons.Default.Email,
                         contentDescription = "Nombre",
                         tint = Color(0xFFFF9900)
                     )
                 },
                 label = {
                     Text(text = "Nombre Completo")
                 },
                 shape = RoundedCornerShape(12.dp),
                 supportingText = {
                     if (nameError.isNotEmpty()){
                         Text(
                             text = nameError,
                             color = Color.Red
                         )
                     }
                 }
             )
             Spacer(modifier = Modifier.height(30.dp))

             OutlinedTextField(
                 value = inputPassword,
                 onValueChange = {inputPassword = it},
                 modifier = Modifier.fillMaxWidth(),
                 leadingIcon = {
                     Icon(
                         imageVector = Icons.Default.Lock,
                         contentDescription = "Contraseña",
                         tint = Color(0xFFFF9900)
                     )
                 },
                 label = {
                     Text(text = "Contraseña")
                 },
                 shape = RoundedCornerShape(12.dp),
                 supportingText = {
                     if (passwordError.isNotEmpty()){
                         Text(
                             text = passwordError,
                             color = Color.Red
                         )
                     }
                 }
             )

             Spacer(modifier = Modifier.height(30.dp))

             OutlinedTextField(
                 value = inputPasswordConfirmation,
                 onValueChange = {inputPasswordConfirmation = it},
                 modifier = Modifier.fillMaxWidth(),
                 leadingIcon = {
                     Icon(
                         imageVector = Icons.Default.Lock,
                         contentDescription = "Contraseña",
                         tint = Color(0xFFFF9900)
                     )
                 },
                 label = {
                     Text(text = "Confimar Contraseña")
                 },
                 shape = RoundedCornerShape(12.dp),
                 supportingText = {
                     if (passwordConfirmationError.isNotEmpty()){
                         Text(
                             text = passwordConfirmationError,
                             color = Color.Red
                         )
                     }
                 }
             )

             if (registerError.isNotEmpty()){
                 Text(registerError, color = Color.Red)
             }

             Spacer(modifier = Modifier.height(30.dp))

             Button(onClick = {
                 val isValidName = validateName(inputName).first
                 val isValidEmail = validateEmail(inputEmail).first
                 val isValidPassword = validatePassword(inputPassword).first
                 val isValidConfirmPassword = validateConfirmPassword(inputPassword, inputPasswordConfirmation).first

                 nameError = validateName(inputName).second
                 emailError = validateEmail(inputEmail).second
                 passwordError = validatePassword(inputPassword).second
                 passwordConfirmationError = validateConfirmPassword(inputPassword, inputPasswordConfirmation).second

                 if (isValidName && isValidPassword && isValidConfirmPassword && isValidEmail){
                     auth.createUserWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(activity){task ->
                         if (task.isSuccessful){
                             onSuccessfulRegister()
                         }else{
                             registerError = when(task.isSuccessful){
                                 is FirebaseAuthInvalidCredentialsException -> "Correo invalido"
                                 is FirebaseAuthUserCollisionException -> "Correo ya registrado"
                                 else -> "Error al registrarse"
                             }
                         }
                     }

                 }else {
                     registerError = "Hubo un error en el register"
                 }

             }, modifier = Modifier.fillMaxWidth()
                 .height(50.dp),
                 shape = RoundedCornerShape(12.dp),
                 colors = ButtonDefaults.buttonColors(
                     containerColor = Color(0xFFFF9900)
                 )
             ) {
                 Text("Registrarse")
             }
         }
     }
    }

@Preview
@Composable
fun RegisterScreenPreview(){
   // RegisterScreen()
}