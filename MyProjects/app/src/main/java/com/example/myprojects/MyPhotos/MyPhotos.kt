package com.example.myphotos

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myprojects.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MyPhotos(navController: NavHostController) {
    var selectedImage by remember { mutableStateOf<ImageData?>(null) }

    Scaffold(bottomBar = ({ com.example.myprojects.MyBottomNavigation(navController) })) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        )
        {
            LazyRow(
                modifier = Modifier
                    .height(130.dp)
                    .fillMaxWidth()
            ) {
                items(getAboutData()) { about ->
                    ItemAbout(imageData = about) {
                        selectedImage = about
                    }

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            if (selectedImage != null) {
                Surface {
                    Image(
                        painter = painterResource(id = selectedImage!!.photo),
                        contentDescription = "Game Image",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            } else {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent,
                ) { }
            }
        }
    }


}


data class ImageData(
    @DrawableRes var photo: Int
)

fun getAboutData(): List<ImageData> {
    return listOf(
        ImageData(
            R.drawable.image1
        ),
        ImageData(
            R.drawable.image2
        ),
        ImageData(
            R.drawable.image3
        ),
        ImageData(
            R.drawable.image4
        ),
        ImageData(
            R.drawable.image5
        ),
        ImageData(
            R.drawable.image6
        ),
        ImageData(
            R.drawable.image7
        ),
        ImageData(
            R.drawable.image8
        ),
    )
}

@Composable
fun ItemAbout(imageData: ImageData, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(180.dp)
            .padding(end = 5.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageData.photo),
            contentDescription = "Game Image",
            modifier = Modifier
                .fillMaxSize()
        )

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
