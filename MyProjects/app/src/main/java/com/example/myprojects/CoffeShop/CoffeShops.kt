package com.example.cafeshop

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myprojects.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

//Tenemos la BottomNavigation como siempre y una columna con las cartas de cafeterias
fun CoffeeShops(navController: NavHostController) {
    Scaffold(bottomBar = ({ com.example.myprojects.MyBottomNavigation(navController) })) {
        Box (modifier = Modifier.padding(bottom = it.calculateBottomPadding())){
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(getCoffee()) { coffee ->
                    itemCoffee(coffee = coffee, navController = navController)
                }
            }
        }
    }
}


//Nombres e imagenes de las cafeterias
fun getCoffee(): List<CoffeeInfo> {
    return listOf(
        CoffeeInfo(
            "Antico Caffè Greco",
            "St. Italy, Rome",
            R.drawable.images
        ),
        CoffeeInfo(
            "Coffee Room",
            "St. Germany, Berlin",
            R.drawable.images1
        ),
        CoffeeInfo(
            "Coffee Ibiza",
            "St. Colon, Madrid",
            R.drawable.images2
        ),
        CoffeeInfo(
            "Pudding Coffee Shop",
            "St. Diagonal, Barcelona",
            R.drawable.images3
        ),
        CoffeeInfo(
            "L'Express",
            "St. Picadilly Circus, London",
            R.drawable.images4
        ),
        CoffeeInfo(
            "Coffee Corner",
            "St. Àngl Guimerà, Valencia",
            R.drawable.images5
        ),
        CoffeeInfo(
            "Sweet Cup",
            "St. Kinkerstraat, Amsterdam",
            R.drawable.images6
        )
    )
}

//Creacion de cada carta, con su RatingBar, su boton.Y al darle a la carta navega a Comentarios
@Composable
fun itemCoffee(coffee: CoffeeInfo,navController: NavHostController) {
    var rating by remember { mutableStateOf(0) }

    Card (
        modifier = Modifier
            .padding(top = 25.dp)
            .clickable { navController.navigate("Comentarios/${coffee.name}") },
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column {
            Image(
                painter = painterResource(id = coffee.image),
                contentDescription = "Coffee Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier
                .size(10.dp)
            )
            Text(
                text = coffee.name,
                fontSize = 27.sp
            )
            RatingBar(modifier = Modifier.padding(start = 10.dp),
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                }
            )
            Text(
                text = coffee.direction
            )
            Spacer(modifier = Modifier
                .size(5.dp)
            )
            Divider()
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "RESERVE",
                    color = Color.DarkGray,
                )
            }
        }
    }
}


//Barra Estrellitas
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 5,
    starsColor: Color = Color.Black,
    onRatingChanged: (Int) -> Unit
) {
    Row(modifier = modifier) {
        repeat(stars) { starIndex ->
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = if (starIndex < rating) Color.Black else Color.White,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clickable { onRatingChanged(starIndex + 1) }
            )
        }
    }
}

