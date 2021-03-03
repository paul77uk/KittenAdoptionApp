package com.example.kittenadoptionapp

import android.content.res.Configuration
import android.graphics.Color.BLACK
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion.FillHeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kittenadoptionapp.ui.theme.KittenAdoptionAppTheme
import org.intellij.lang.annotations.JdkConstants

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KittenAdoptionAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background)
                {
                    ScaffoldAppBar()
                }
            }
        }
    }
}

@Composable
fun CardComposable(text: String, resourceId: Int) {
    Card(
        modifier = Modifier
//                            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),

//                border = BorderStroke(0.5.dp, Color.Black),
        elevation = 16.dp,
        shape = RoundedCornerShape(4.dp)
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
fun TwoColumns(text: String, text2: String, resourceId: Int, resourceId2: Int) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId)
        CardComposable(text = text2, resourceId = resourceId2)
    }
}

@Composable
fun ThreeColumns(
    text: String, text2: String, text3: String,
    resourceId: Int, resourceId2: Int, resourceId3: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CardComposable(text = text, resourceId = resourceId)
        CardComposable(text = text2, resourceId = resourceId2)
        CardComposable(text = text3, resourceId = resourceId3)
    }
}


@Composable
fun KittenList() {

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
                            resourceId2 = R.drawable.mainecoon2
                        )
                        TwoColumns(
                            text = "Kitten 3",
                            text2 = "Kitten 4",
                            resourceId = R.drawable.mainecoon3,
                            resourceId2 = R.drawable.mainecoon5
                        )
                        TwoColumns(
                            text = "Kitten 5",
                            text2 = "Kitten 6",
                            resourceId = R.drawable.mainecoon6,
                            resourceId2 = R.drawable.mainecoon7
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
                            )
                            ThreeColumns(
                                text = "Kitten 5",
                                text2 = "Kitten 6",
                                text3 = "Kitten 7",
                                resourceId = R.drawable.mainecoon5,
                                resourceId2 = R.drawable.mainecoon6,
                                resourceId3 = R.drawable.mainecoon7,
                            )
                    }
                }
            }


            Spacer(Modifier.height(4.dp))

        }
    }
}

@Composable
fun ScaffoldAppBar() {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Kitten Adoption App",
                    )
                },

                )
        },
    ) {

    KittenList(

        )

    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    KittenAdoptionAppTheme {
//        KittenList()
//    }
//}
