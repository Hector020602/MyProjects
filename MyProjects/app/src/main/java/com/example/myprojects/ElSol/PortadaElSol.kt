package com.example.elsol

import android.widget.Toast
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprojects.MyBottomNavigation
import com.example.myprojects.R
import com.example.myprojects.ui.theme.ElSolTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortadaElSol(navController: NavHostController){
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    },
        topBar = {
            BottomAppBar(drawerState = drawerState)


        }, bottomBar = { MyBottomNavigation(navController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding(), top = it.calculateTopPadding())
        ) {

            //Creacion del desplegable lateral, con 3 opciones para que al clickar navegue

            val items = listOf(Icons.Default.Build, Icons.Default.Info, Icons.Default.Email)
            val selectedItem = remember {
                mutableStateOf(items[0])
            }

            //Desplegable Lateral
            ModalNavigationDrawer(drawerState = drawerState,
                drawerContent = {
                    ModalDrawerSheet {
                        Image(
                            painter = painterResource(id = R.drawable.corona_solar),
                            contentDescription = "Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        items.forEach { item ->
                            NavigationDrawerItem(
                                icon = { Icon(item, contentDescription = null) },
                                label = { Text(text = item.name.substringAfter(".")) },
                                selected = item == selectedItem.value,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                    navController.navigate(item.name)
                                })
                        }
                    }
                }, content = {
                    SolVerticalGrid(snackbarHostState)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = it.calculateBottomPadding())
                    ) {
                    }
                })
        }
    }
}


//Creacion de las cartas del sol en 2 filas
@Composable
fun SolVerticalGrid(snackbarHostState: SnackbarHostState) {
    val context = LocalContext.current
    val solList = remember { getSol() }
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(solList) { solInfo ->
            itemSol(solInfo = solInfo, snackbarHostState = snackbarHostState)
        }
    }
}


//Imagenes
fun getSol(): List<SolInfo> {
    return listOf(
        SolInfo(
            "Corona Solar",
            R.drawable.corona_solar
        ),
        SolInfo(
            "Erupcion Solar",
            R.drawable.erupcionsolar
        ),
        SolInfo(
            "Espiculas",
            R.drawable.espiculas
        ),
        SolInfo(
            "Filamentos",
            R.drawable.filamentos
        ),
        SolInfo(
            "Magnetosfera",
            R.drawable.magnetosfera
        ),
        SolInfo(
            "Mancha Solar",
            R.drawable.manchasolar
        ),
    )
}

//Cada una de las cartas individualmente
@Composable
fun itemSol(solInfo: SolInfo,snackbarHostState: SnackbarHostState) {
    var scope = rememberCoroutineScope()
    var expanded by remember { mutableStateOf(false) }
    Card (
        modifier = Modifier
            .padding(top = 5.dp)
            .padding(horizontal = 5.dp)
            .clickable {scope.launch { snackbarHostState.showSnackbar(solInfo.name) }},
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column {
            Image(
                painter = painterResource(id = solInfo.image),
                contentDescription = "Coffee Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier
                .size(10.dp)
            )
            Row {
                Text(
                    text = solInfo.name,
                    modifier = Modifier
                        .weight(3f)
                        .padding(5.dp),
                    fontSize = 18.sp
                )
                IconButton(onClick = {expanded = !expanded}) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        modifier = Modifier.weight(1f),
                        contentDescription = "Buscar", tint = Color.Black,
                    )
                }

                //Menu de los 3 puntitos
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = !expanded}) {
                    DropdownMenuItem(text = { Text(text = "Compartir") },
                        onClick = { /*TODO*/ }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Share, contentDescription = "Compartir"
                            )
                        })
                    DropdownMenuItem(text = { Text(text = "Album") },
                        onClick = { /*TODO*/ }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock, contentDescription = "Album"
                            )
                        })
                }

            }
            Spacer(modifier = Modifier
                .size(5.dp)
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable


//Barra de arriba con: la flecha para ir al desplegable, el Corazon (BadgedBox) y el + (FAB)
fun BottomAppBar(drawerState: DrawerState) {
    var badgeCount by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    androidx.compose.material3.BottomAppBar(containerColor = Color.Red) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        onClick = {
                            //Importante para poder navegar al Desplegable
                            scope.launch { drawerState.open() }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                    BadgedBox(badge = {
                        Badge {
                            Text(text = badgeCount.toString())
                        }

                    }, modifier = Modifier
                        .padding(10.dp)
                        .clickable { badgeCount++ }) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
                Row {
                    FloatingActionButton(onClick = { /*TODO*/ }, containerColor = Color.Black) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

class SolInfo (
    val name: String,
    val image: Int,
)