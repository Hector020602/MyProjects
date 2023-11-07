package com.example.myprojects

import android.annotation.SuppressLint
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Portada(navController: NavHostController){

    Scaffold(bottomBar = ({ MyBottomNavigation(navController) })) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "MyProjects",
                textAlign = TextAlign.Center,
                fontSize = 50.sp,)
        }

    }
}

@Composable
fun MyBottomNavigation(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("MyPhotos") },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            label = { Text("MyPhotos") })

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("CoffeShops") },
            icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "") },
            label = { Text("CoffeeShops") })

        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("ElSol")},
            icon = { Icon(imageVector = Icons.Default.Star, contentDescription = "") },
            label = { Text("ElSol") })
    }
}


