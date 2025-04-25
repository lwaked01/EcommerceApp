package com.leonardoWaked.ecommerceapp

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(myNavController: NavController) {

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val activity = LocalView.current.context as Activity


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Image(
                painter = painterResource(R.drawable.logo_unab),
                contentDescription = "Logo Unab",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(31.dp))

            Text(
                text = "Iniciar Sesion",
                fontSize = 24.sp,
                color = Color(0xFFFF9900),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = inputEmail,
                onValueChange = { inputEmail = it },
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
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = inputPassword,
                onValueChange = { inputPassword = it },
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
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(onClick = {

                val auth = Firebase.auth

                auth.signInWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(activity){
                    task ->
                    if(task.isSuccessful){
                        myNavController.navigate("home")
                    }else{
                        Log.i("login", "Hubo un error")
                    }
                }


            }, modifier = Modifier.fillMaxWidth()
                .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9900)
                )
            ) {
                Text("Iniciar Sesion")
            }
            Spacer(modifier = Modifier.height(30.dp))

            TextButton(onClick = {
                myNavController.navigate("register")
            }) {
                Text(
                    text = "¿No tienes una cuenta?",
                    color = Color(0xFFFF9900)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    //LoginScreen()
}

fun validateEmail (email:String): Pair<Boolean,String>{

    return  when{
        email.isEmpty()-> Pair(false, "El correo es obligatorio")
        !email.endsWith("@unab.edu.co") -> Pair(false, "El correo debe ser unab")

        else -> Pair(true, "")
    }

}