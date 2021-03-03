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

//                                elevation = 16.dp,
//                border = BorderStroke(0.5.dp, Color.LightGray),
//                shape = RoundedCornerShape(4.dp)

                Text(text = text, modifier = Modifier.padding(8.dp))

            Spacer(Modifier.height(4.dp))

        }
    }
}

@Composable
fun TwoColumns(
    text: String, text2: String, resourceId: Int, resourceId2: Int, navFunc: () -> Unit,
    navFunc2: () -> Unit

) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId, navFunc = navFunc)
        CardComposable(text = text2, resourceId = resourceId2, navFunc = navFunc2)
    }
}

@Composable
fun ThreeColumns(
    text: String, text2: String, text3: String,
    resourceId: Int, resourceId2: Int, resourceId3: Int, navFunc: () -> Unit,
    navFunc2: () -> Unit, navFunc3: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId, navFunc = navFunc)
        CardComposable(text = text2, resourceId = resourceId2, navFunc = navFunc2)
        CardComposable(text = text3, resourceId = resourceId3, navFunc = navFunc3)
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
                            navFunc = { navController.navigate("kitten1Screen") },
                            navFunc2 = { navController.navigate("kitten2Screen") }
                        )
                        TwoColumns(
                            text = "Kitten 3",
                            text2 = "Kitten 4",
                            resourceId = R.drawable.mainecoon3,
                            resourceId2 = R.drawable.mainecoon5,
                            navFunc = { navController.navigate("kitten3Screen") },
                            navFunc2 = { navController.navigate("kitten4Screen") }
                        )
                        TwoColumns(
                            text = "Kitten 5",
                            text2 = "Kitten 6",
                            resourceId = R.drawable.mainecoon6,
                            resourceId2 = R.drawable.mainecoon7,
                            navFunc = { navController.navigate("kitten5Screen") },
                            navFunc2 = { navController.navigate("kitten6Screen") }
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
                            navFunc = { navController.navigate("kitten1Screen") },
                            navFunc2 = { navController.navigate("kitten2Screen") },
                            navFunc3 = { navController.navigate("kitten3Screen") }
                        )
                        ThreeColumns(
                            text = "Kitten 5",
                            text2 = "Kitten 6",
                            text3 = "Kitten 7",
                            resourceId = R.drawable.mainecoon5,
                            resourceId2 = R.drawable.mainecoon6,
                            resourceId3 = R.drawable.mainecoon7,
                            navFunc = { navController.navigate("kitten4Screen") },
                            navFunc2 = { navController.navigate("kitten5Screen") },
                            navFunc3 = { navController.navigate("kitten6Screen") }
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
        composable("kitten2Screen") { Kitten2Screen() }
        composable("kitten3Screen") { Kitten3Screen() }
        composable("kitten4Screen") { Kitten4Screen() }
        composable("kitten5Screen") { Kitten5Screen() }
        composable("kitten6Screen") { Kitten6Screen() }

    }
}

@Composable
fun KittenScreen(name: String, image: Int) {
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
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier


//                        .padding(horizontal = 16.dp)
//                .scale(1.6f)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Text(
                text = name,
                fontSize = 24.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                SmallCards("4 months", "Age")
                SmallCards("Grey", "Color")
                SmallCards("11 kg", "Weight")
            }
        }

    }

}

@Composable
fun Kitten1Screen() {
    KittenScreen(name = "Kitten 1", image = R.drawable.mainecoon1)
}

@Composable
fun Kitten2Screen() {
    KittenScreen(name = "Kitten 2", image = R.drawable.mainecoon2)
}

@Composable
fun Kitten3Screen() {
    KittenScreen(name = "Kitten 3", image = R.drawable.mainecoon3)
}

@Composable
fun Kitten4Screen() {
    KittenScreen(name = "Kitten 4", image = R.drawable.mainecoon5)
}

@Composable
fun Kitten5Screen() {
    KittenScreen(name = "Kitten 5", image = R.drawable.mainecoon6)
}

@Composable
fun Kitten6Screen() {
    KittenScreen(name = "Kitten 6", image = R.drawable.mainecoon7)
}

@Composable
fun SmallCards(text: String, text2: String) {
    Card(
        modifier = Modifier,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(4.dp),
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = text2,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.LightGray,
            )
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
