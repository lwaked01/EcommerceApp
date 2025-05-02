package com.leonardoWaked.ecommerceapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReusableContentHost
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onClickLogOut: () -> Unit = {}){

    val auth = Firebase.auth
    val user = auth.currentUser



    Scaffold(
        topBar = {
            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),

                title = {
                    Text(
                        "Bienvenido",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { auth.signOut() }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ){ innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){

            if(user != null){
                Text(user.email ?: "No hay usuario")
            }else{
                Text("No hay usuario")
            }

            Text(
                text = "Promociones Destacadas",
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp)
            )

            val listadoPromociones = listOf(
                "https://img.freepik.com/vector-premium/banner-promocion-vectorial-2x1-diseno-plantilla-editable-efecto-texto_333987-3139.jpg",
                "https://png.pngtree.com/png-clipart/20230620/original/pngtree-special-promo-banner-shape-for-promotion-of-your-business-vector-png-image_9192296.png",
                "https://img.freepik.com/vetores-premium/desenho-geometrico-modelo-de-promocao-de-banner_151987-439.jpg",
                "https://static.vecteezy.com/system/resources/previews/000/178/364/non_2x/super-sale-offer-and-discount-banner-template-for-marketing-and-vector.jpg",
            )

            LazyRow (
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ){
                item { CardPromo(listadoPromociones[0]) }
                item { CardPromo(listadoPromociones[1]) }
                item { CardPromo(listadoPromociones[2]) }
                item { CardPromo(listadoPromociones[3]) }


            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}


@Composable
fun CardPromo(urlImage : String){
    Card (modifier = Modifier
        .height(180.dp)
        .width(300.dp),
        shape = RoundedCornerShape(16.dp)){
        Image(
            painter = rememberAsyncImagePainter(urlImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}