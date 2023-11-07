package com.example.myprojects

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cafeshop.CoffeeShops
import com.example.cafeshop.Comentarios
import com.example.elsol.Email
import com.example.elsol.Info
import com.example.elsol.PortadaElSol
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
                        composable("CoffeeShops") { CoffeeShops(navController) }
                        composable("ElSol"){ PortadaElSol(navController)}
                        composable("Comentarios/{cafeteriaName}",
                            arguments = listOf(navArgument("cafeteriaName")
                            {type = NavType.StringType}))
                        {backStackEntry ->
                            Comentarios(backStackEntry.arguments?.getString("cafeteriaName") ?: "", navController)
                        }
                        composable("Principal") { PortadaElSol(navController) }
                        composable("Filled.Email") { Email(navController) }
                        composable("Filled.Info") { Info(navController) }
                        composable("Filled.Build") { PortadaElSol(navController) }
                    }
                }
            }
        }
    }
}