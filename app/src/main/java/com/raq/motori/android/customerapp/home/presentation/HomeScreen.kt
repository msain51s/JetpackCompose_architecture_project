package com.raq.motori.android.customerapp.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.google.firebase.analytics.FirebaseAnalytics
import com.raq.motori.android.customerapp.R
import com.raq.motori.android.customerapp.home.presentation.viewmodel.HomeViewModel
import com.raq.motori.android.customerapp.utility.AnalyticsEventHandler
import com.raq.motori.android.customerapp.utility.EventType
import com.raq.motori.android.customerapp.utility.components.RMLoader

/**
 * Created by Manoj Sain on 10/01/24.
 */

@Composable
 fun HomeScreen(onClick:() -> Unit){
    val viewModel: HomeViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()

    HomeContents(states = viewState)

    //Api call for one time
    LaunchedEffect(key1 = Unit){
        viewModel.getProducts()

        AnalyticsEventHandler.logEvent(
            EventType.SCREEN_VIEW,
            Bundle().apply {
                putString(FirebaseAnalytics.Param.SCREEN_NAME, "HomeScreen")
            }
        )
    }
}

@Composable
fun HomeContents(
    states: HomeViewStates
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            RMLoader(isShowingDialog = states.isLoading)

            Column {
                Log.d("arabic_text",stringResource(id = R.string.services_text))
                ImageBuilder(image = R.drawable.home_banner2)
                ServiceTitle(title = stringResource(id = R.string.services_text))
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(top = it.calculateTopPadding()),
                    columns = StaggeredGridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 10.dp
                ) {

                    items(states.products) { product ->
                        ServiceItem(text = product.title)
                    }
                }
            }
        }
    }
}

    /////// FixMe : sample design temporary
    @Composable
    fun ImageBuilder(image: Int) {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(SvgDecoder.Factory())
            }

            .build()

        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Image(
                painter = rememberAsyncImagePainter(image, imageLoader),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }

@Composable
fun ServiceItem(text:String) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .wrapContentSize()
            .padding(7.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.service_name),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
            Text(
                text = stringResource(id = R.string.service_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Text(
                text = stringResource(id = R.string.start_service),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color.Red
                )
            )
        }
    }
}

@Composable
fun ServiceTitle(title:String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(7.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Text(text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp),
            style = TextStyle(
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        ))
    }
}
