package com.example.myprojects

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cafeshop.CoffeeInfo
import com.example.cafeshop.CoffeeShops
import com.example.cafeshop.Comentarios
import com.example.cafeshop.MyTopAppBar
import com.example.myphotos.MyPhotos
import com.example.myprojects.ui.theme.MyProjectsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyProjectsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Portada"){
                        composable("Portada"){ Portada(navController)}
                        composable("MyPhotos"){ MyPhotos(navController)}
                        composable("CoffeShops"){
                            Scaffold(topBar = { MyTopAppBar() })
                            {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(top = it.calculateTopPadding())
                                )
                                {
                                    val navController = rememberNavController()
                                    NavHost(navController = navController, startDestination = "Card") {
                                        composable("Card") { CoffeeShops(navController) }
                                        composable("Comentarios/{cafeteriaName}",
                                            arguments = listOf(navArgument("cafeteriaName")
                                            {type = NavType.StringType}))
                                        {backStackEntry ->
                                            Comentarios(backStackEntry.arguments?.getString("cafeteriaName") ?: "", navController)
                                        }
                                    }

                                }

                            }
                         /*  Surface(modifier = Modifier.fillMaxSize())
                            {
                                CoffeeShops(navController)
                           }*/
                        }
                        composable("ElSol"){
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                val navController = rememberNavController()
                                NavHost(navController = navController,startDestination = "Portada"){
                                    composable("Portada"){ com.example.elsol.Principal(navController)}
                                    composable("ClaseInfo"){ Portada(navController) }
                                }
                            }
                        }
                    }
                    @Composable
                    fun GreetingPreview() {
                     /*   MyProjectsTheme {
                        }*/
                    }
                }
            }
        }
    }
}