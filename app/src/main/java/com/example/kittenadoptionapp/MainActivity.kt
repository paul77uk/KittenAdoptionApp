package com.example.kittenadoptionapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.kittenadoptionapp.ui.theme.KittenAdoptionAppTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KittenAdoptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background)
                {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun CardComposable(
    text: String, resourceId: Int, navFunc: () -> Unit
) {
    Card(
        modifier = Modifier
//                            .fillMaxWidth()
//            .padding(horizontal = 8.dp
            .clickable(onClick = { navFunc() })
            .padding(top = 16.dp),

//                border = BorderStroke(0.5.dp, Color.Black),
        elevation = 16.dp,
        shape = RoundedCornerShape(4.dp),
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = "",
//                                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 16.dp)
//                                    .scale(1.6f)
                    .clip(shape = RoundedCornerShape(4.dp))
//                    .fillMaxSize()
            )
            Spacer(Modifier.height(4.dp))
            Card(
//                                elevation = 16.dp,
                border = BorderStroke(0.5.dp, Color.LightGray),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = text, modifier = Modifier.padding(8.dp))
            }
            Spacer(Modifier.height(4.dp))

        }
    }
}

@Composable
fun TwoColumns(
    text: String, text2: String, resourceId: Int, resourceId2: Int, navFunc: () -> Unit

) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId, navFunc = navFunc)
        CardComposable(text = text2, resourceId = resourceId2, navFunc = navFunc)
    }
}

@Composable
fun ThreeColumns(
    text: String, text2: String, text3: String,
    resourceId: Int, resourceId2: Int, resourceId3: Int, navFunc: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId, navFunc = navFunc)
        CardComposable(text = text2, resourceId = resourceId2, navFunc = navFunc)
        CardComposable(text = text3, resourceId = resourceId3, navFunc = navFunc)
    }
}


@Composable
fun KittenList(navController: NavController) {

    val scrollState = rememberLazyListState()

    LazyColumn(
//        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState,
//        modifier = Modifier
//            .scale(1.5f),

    ) {
        items(1) {

            BoxWithConstraints {
                if (maxWidth < 480.dp) {
                    Column {
                        TwoColumns(
                            text = "Kitten 1",
                            text2 = "Kitten 2",
                            resourceId = R.drawable.mainecoon1,
                            resourceId2 = R.drawable.mainecoon2,
                            navFunc = { navController.navigate("kitten1Screen") }
                        )
                        TwoColumns(
                            text = "Kitten 3",
                            text2 = "Kitten 4",
                            resourceId = R.drawable.mainecoon3,
                            resourceId2 = R.drawable.mainecoon5,
                            navFunc = { navController.navigate("kitten1Screen") }
                        )
                        TwoColumns(
                            text = "Kitten 5",
                            text2 = "Kitten 6",
                            resourceId = R.drawable.mainecoon6,
                            resourceId2 = R.drawable.mainecoon7,
                            navFunc = { navController.navigate("kitten1Screen") }
                        )
                    }
                } else {
                    Column {
                        ThreeColumns(
                            text = "Kitten 1",
                            text2 = "Kitten 2",
                            text3 = "Kitten 3",
                            resourceId = R.drawable.mainecoon1,
                            resourceId2 = R.drawable.mainecoon2,
                            resourceId3 = R.drawable.mainecoon3,
                            navFunc = { navController.navigate("kitten1Screen") }
                        )
                        ThreeColumns(
                            text = "Kitten 5",
                            text2 = "Kitten 6",
                            text3 = "Kitten 7",
                            resourceId = R.drawable.mainecoon5,
                            resourceId2 = R.drawable.mainecoon6,
                            resourceId3 = R.drawable.mainecoon7,
                            navFunc = { navController.navigate("kitten1Screen") }
                        )
                    }
                }
            }


            Spacer(Modifier.height(4.dp))

        }
    }
}

@Composable
fun ScaffoldAppBar(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,

                title = {
                    Text(
                        text = "Kitten Adoption App",
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    )
                },

                )
        },
    ) {

        KittenList(
            navController = navController
        )

    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "scaffoldAppBar"
    ) {

        composable("scaffoldAppBar") { ScaffoldAppBar(navController) }
        composable("kitten1Screen") { Kitten1Screen() }

    }
}

@Composable
fun Kitten1Screen() {
    val scrollState = rememberLazyListState()

    LazyColumn(
//        horizontalAlignment = Alignment.CenterHorizontally,
        state = scrollState,
        modifier = Modifier
            .padding(16.dp)
//            .padding(top = 16.dp)
//            .padding(horizontal = 16.dp)
//            .scale(1.6f)
//            .clip(shape = RoundedCornerShape(4.dp))


    ) {
        items(1) {

            Image(

//                    alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.mainecoon1),
                contentDescription = null,
                modifier = Modifier


//                        .padding(horizontal = 16.dp)
//                .scale(1.6f)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = "Caramel",
                fontSize = 24.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 24.dp)
            )
            Row {
                SmallCards()
                SmallCards()
                SmallCards()
            }
        }

    }

}

@Composable
fun SmallCards() {
    Card( modifier = Modifier,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(4.dp),) {

        Column(Modifier.padding(16.dp)) {
            Text(text = "4 months")
            Text(text = "4 months")
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    KittenAdoptionAppTheme {
//        KittenList()
//    }
//}
