/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.androiddevchallenge.ui.theme.MyTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(this@MainActivity, android.R.color.transparent)
        }
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

data class Friend(
    val name: String,
    val type: String,
    val image: Int,
    val age: String,
    val location: String,
    val isMale : Boolean,
    val backgroundColor: Color,
    val secondaryColor: Color
)

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, "listScreen") {
        composable("listScreen") { ListScreen(navController = navController) }
        composable("detailScreen") { DetailScreen() }
    }
}

@Composable
fun ListScreen(navController: NavController) {
    val list = listOf(
        Friend(
            name = "Spock",
            type = "Golden",
            image = R.drawable.puppy_golden,
            age = "6 months",
            location = "Luisiana",
            isMale = true,
            backgroundColor = Color(0xFF30a27c),
            secondaryColor = Color(0xFF207654)
        ),
        Friend(
            name = "Chuck",
            type = "Golden",
            image = R.drawable.puppy_petit_chiot,
            age = "6 months",
            location = "Missisipi",
            isMale = false,
            backgroundColor = Color(0xFFf8c360),
            secondaryColor = Color(0xFFf79f41)
        ),
        Friend(
            name = "Snoopy",
            type = "Golden",
            image = R.drawable.puppy_1,
            age = "6 months",
            location = "Kadıköy",
            isMale = true,
            backgroundColor = Color(0xFFb0cff1),
            secondaryColor = Color(0xFF4f82be)
        ),
        Friend(
            name = "Karanlık",
            type = "Koala",
            image = R.drawable.koala,
            age = "25 years",
            location = "Kartal",
            isMale = false,
            backgroundColor = Color(0xFFba68c8),
            secondaryColor = Color(0xFF7b1fa2)
        ),
        Friend(
            name = "Spock",
            type = "Golden",
            image = R.drawable.puppy_golden,
            age = "6 months",
            location = "Luisiana",
            isMale = true,
            backgroundColor = Color(0xFF30a27c),
            secondaryColor = Color(0xFF207654)
        ),
        Friend(
            name = "Chuck",
            type = "Golden",
            image = R.drawable.puppy_petit_chiot,
            age = "6 months",
            location = "Missisipi",
            isMale = false,
            backgroundColor = Color(0xFFf8c360),
            secondaryColor = Color(0xFFf79f41)
        )
    )

    Surface(color = MaterialTheme.colors.background) {
        Column {
            LazyColumn {
                itemsIndexed(list) { index, item ->
                    val backColor = if (index == 0) {
                        list[index].backgroundColor
                    } else {
                        list[index - 1].backgroundColor
                    }
                    AnimalCard(
                        modifier = Modifier.background(backColor)
                            .clickable(onClick = {
                                navController.navigate(route = "detailScreen")
                            }),
                        item
                    )
                }
            }
        }

    }
}

@Composable
fun AnimalCard(modifier: Modifier, friend: Friend) {
    Card(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp),
        backgroundColor = friend.backgroundColor
    ) {

        Row {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 48.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    modifier = Modifier.size(108.dp, 148.dp),
                    painter = painterResource(id = friend.image),
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 48.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = friend.name, style = TextStyle(
                            color = Color(0xFFE7F4F0),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Text(text = friend.type, style = TextStyle(
                    color = Color(0xCCE7F4F0),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                ), modifier = Modifier.padding(start = 2.dp))

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Surface(
                        color = friend.secondaryColor,
                        elevation = 2.dp,
                        shape = RoundedCornerShape(size = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = friend.age, style = TextStyle(
                                color = Color(0xCCE7F4F0),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Surface(
                        color = friend.secondaryColor,
                        elevation = 2.dp,
                        shape = RoundedCornerShape(size = 12.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(4.dp),
                            text = friend.location, style = TextStyle(
                                color = Color(0xCCE7F4F0),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Surface(
                        // Color(0xFF4FC3F7)
                        color = Color(0xFFeeeeee),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(size = 12.dp)
                    ) {
                        Icon(
                            imageVector = if (friend.isMale) ImageVector.vectorResource(id = R.drawable.gender_male)
                            else ImageVector.vectorResource(id = R.drawable.gender_female),
                            tint = if (friend.isMale) Color(0xFF3F51B5)
                            else Color(0xFFE91E63),
                            contentDescription = "Gender Icon",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                        )
                    }

                }
            }
        }
    }
}

@Composable
@Preview
fun DetailScreen() {
    val friend = Friend(
        name = "Spock",
        type = "Petit-Chiot",
        image = R.drawable.puppy_petit_chiot,
        age = "6 months",
        location = "Luisiana",
        isMale = true,
        backgroundColor = Color(0xFF30a27c),
        secondaryColor = Color(0xFF207654)
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.linearGradient(
                    colorStops = arrayOf(
                        Pair(0.0f, friend.backgroundColor),
                        Pair(0.5f, Color(0xFA33aa84)),
                        Pair(1.0f, Color(0xFA2c9673))
                    )
                )
            )
    ) {

        val (heartIcon, animalImage, title, contact, desc, adopt) = createRefs()

        HeartIcon(Modifier.constrainAs(heartIcon) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        AnimalImage(Modifier.constrainAs(animalImage) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }, friend.image)

        AnimalTitle(Modifier.constrainAs(title) {
            top.linkTo(animalImage.bottom)
            start.linkTo(parent.start)
        }, friend.name, friend.type)

        ContactInformation(Modifier.constrainAs(contact) {
            top.linkTo(title.top)
            bottom.linkTo(title.bottom)
            end.linkTo(parent.end)
        }, Color(0xFF86c9b4))

        AnimalDesc(Modifier.constrainAs(desc) {
            top.linkTo(title.bottom, margin = 8.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        AdoptButton(Modifier.constrainAs(adopt) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun HeartIcon(modifier: Modifier) {
    Surface(
        // 0xFF80d8ff
        color = Color(0xFF86c9b4),
        elevation = 2.dp,
        shape = RoundedCornerShape(size = 12.dp),
        modifier = modifier
            .size(48.dp)

    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_heart),
            tint = Color(0xFFE7F4F0),
            contentDescription = "Favorite",
            modifier = modifier
                .size(24.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun AnimalImage(modifier: Modifier, image: Int) {
    Image(
        modifier = modifier.size(280.dp, 280.dp),
        painter = painterResource(id = image),
        contentDescription = null
    )
}

@Composable
fun AdoptButton(modifier: Modifier) {
        Surface(
            color = Color(0xFF86c9b4),
            elevation = 2.dp,
            shape = RoundedCornerShape(size = 12.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(height = 48.dp)
                .padding(horizontal = 16.dp)

        ) {
            Box {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "ADOPT",
                    color = Color(0xFFE7F4F0),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
}

@Composable
fun AnimalDesc(modifier: Modifier) {
    Text(text =
    "He is very happy and he always runs. He likes catching balls and no worries that he is going to get it soon or later."
        , style = TextStyle(
            color = Color(0xCCE7F4F0),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        ), modifier = modifier.padding(start = 16.dp))
}

@Composable
fun ContactInformation(modifier: Modifier, backgroundColor: Color) {
    Surface(
        // Color(0xFF4FC3F7)
        color = backgroundColor,
        elevation = 2.dp,
        shape = RoundedCornerShape(size = 12.dp),
        modifier = modifier.padding(end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(id = R.drawable.koala),
                contentDescription = "Gender Icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
            )
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_call_24),
                tint = Color(0xFFE7F4F0),
                contentDescription = "Gender Icon",
                modifier = Modifier
                    .size(48.dp)
                    .padding(4.dp)
                    .padding(end = 8.dp)
            )

        }
    }
}

@Composable
fun AnimalTitle(modifier: Modifier, name: String, type: String) {
    Column(
        modifier = modifier.padding(start = 16.dp)
    ) {
        Text(
            text = name, style = TextStyle(
                color = Color(0xFFE7F4F0),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = type, style = TextStyle(
                color = Color(0xFFE7F4F0),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )

    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
