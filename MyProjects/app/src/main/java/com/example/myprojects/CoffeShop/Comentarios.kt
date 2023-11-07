package com.example.cafeshop

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myprojects.MyBottomNavigation

import com.example.myprojects.ui.theme.Pink80


@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter",
    "SuspiciousIndentation"
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Comentarios(navControllerName: String, navController: NavHostController){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val nombreCafeteria = navBackStackEntry?.arguments?.getString("cafeteriaName") ?: ""
    var isMenuVisible by remember { mutableStateOf(false) }
    val listState = rememberLazyStaggeredGridState()
    val buttonVisible = remember { mutableStateOf(true) }
    val comments = listOf<String>(
        "Algunos sueltos, aún así lo recomiendo.",
        "Céntrico y acogedor. Volveremos sanos y salvos.",
        "El entorno muy bueno, pero en el último piso un poco...",
        "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos.",
        "El personal muy amable, nos permitieron ver todo el establecimiento.",
        "Muy bien.","Excelente. Destacar la extensa carta de cafés.","Buen ambiente y buen servicio. Lo recomiendo.",
        "En vacaciones hay mucho tiempo de espera. Los camareros no son suficientes. No lo recomiendo. No volveré." ,
        "Repetiremos. Gran selección de tartas y cafés.", "Todo lo que he probado en la cafetería es rico, dulce o salado." ,
        "Los platos muy bonitos todos de diseño que en el entorno del bar es ideal.",
        "Puntos negativos: el servicio es muy lento y los precios un poco elevados."
    )
    Scaffold (bottomBar = { MyBottomNavigation(navController = navController)}){
        Column (modifier = Modifier.padding(bottom = it.calculateBottomPadding())){
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                Text(
                    text = nombreCafeteria,
                    fontSize = 32.sp,
                    color = Color.Black,
                )
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                state = listState
            ) {
                items(comments.size) { index ->
                    Card(
                        modifier = Modifier.padding(6.dp),
                        elevation = CardDefaults.cardElevation(6.dp),
                        colors = CardDefaults.cardColors(Color.LightGray)
                    ) {
                        Text(
                            text = comments[index],
                            fontSize = 16.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp))
                    }
                }

                val scrollOffset = listState.firstVisibleItemScrollOffset
                if (scrollOffset > 0 && buttonVisible.value) {
                    buttonVisible.value = false
                } else if (scrollOffset == 0 && !buttonVisible.value) {
                    buttonVisible.value = true
                }

            }

        }
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter) {
            if (!buttonVisible.value) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Pink80),
                ) {
                    Text(text = "Add new comment")
                }
            }
        }
    }
}