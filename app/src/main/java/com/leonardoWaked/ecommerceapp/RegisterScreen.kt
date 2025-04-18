package com.leonardoWaked.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(myNavController: NavController){
 Scaffold (
     topBar = {
         TopAppBar(
             title = {},
             navigationIcon = {
                 IconButton(onClick = {
                     myNavController.popBackStack()
                 }) {
                     Icon(Icons.Default.ArrowBack, contentDescription = null)
                 }
             }
         )
     }
 ){ innerPadding ->
     Column (modifier = Modifier.padding(innerPadding)
         .padding(horizontal = 32.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center) {

         Image(imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(20.dp))

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
             value = "",
             onValueChange = {},
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
             value = "",
             onValueChange = {},
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
             shape = RoundedCornerShape(12.dp)
         )
         Spacer(modifier = Modifier.height(30.dp))

         OutlinedTextField(
             value = "",
             onValueChange = {},
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

         OutlinedTextField(
             value = "",
             onValueChange = {},
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
             shape = RoundedCornerShape(12.dp)
         )
         Spacer(modifier = Modifier.height(30.dp))

         Button(onClick = {}, modifier = Modifier.fillMaxWidth()
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