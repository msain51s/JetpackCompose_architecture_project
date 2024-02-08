package com.raq.motori.android.customerapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.raq.motori.android.customerapp.base.presentation.BaseActivity
import com.raq.motori.android.customerapp.navigation.BottomNavigationBar
import com.raq.motori.android.customerapp.screens.temporary.NavHostContainer
import com.raq.motori.android.customerapp.theme.ArchitectureTheme
import com.raq.motori.android.customerapp.utility.components.RMToggleButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val isLoggedIn = true //Todo: based on the user login start related navigation
//                    if(isLoggedIn){
//                        MainNavigation()
//                    }else{
//                        AuthNavigation()
//                    }
                    

              /////FixMe: Temporary screen design

                    val navController: NavHostController = rememberNavController()
                    Scaffold(
                        topBar = {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(Color.White)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(top = 5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    ImageBuilder(image = R.drawable.header_icon)
                                    RMToggleButton {langCode ->
                                        setLanguage(langCode)
                                        super.attachBaseContext(this@MainActivity)
                                        recreate()
                                    }
                                }
                            }
                        },
                        // Bottom navigation
                        bottomBar = {
                            BottomNavigationBar(navController)
                        }, content = { padding ->
                            // Navhost: where screens are placed
                            NavHostContainer(navController = navController, padding = padding)
                        }
                    )
                }
            }
        }
    }
}


////FixMe: Temporary design

@Composable
fun ImageBuilder(image: Int) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }

        .build()
        Image(
            painter = rememberAsyncImagePainter(image, imageLoader),
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArchitectureTheme {
        Greeting("Android")
    }
}