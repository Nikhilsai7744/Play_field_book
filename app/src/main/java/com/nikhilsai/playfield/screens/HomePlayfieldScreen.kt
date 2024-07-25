package com.nikhilsai.playfield.screens

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nikhilsai.playfield.AddtoCartActivity
//import com.example.myproject.DetailsPageActivity

import com.nikhilsai.playfield.R
import com.nikhilsai.playfield.UserProfileActivity
import com.nikhilsai.playfield.Playfieldcomponents.AppToolbar
import com.nikhilsai.playfield.Playfieldcomponents.NavigationDrawerBody
import com.nikhilsai.playfield.Playfieldcomponents.NavigationDrawerHeader

import com.nikhilsai.playfield.Playfielddata.Playfieldhome.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getUserData()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawerHeader(homeViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                onNavigationItemClicked = {
                    Log.d("ComingHere","inside_NavigationItemClicked")
                    Log.d("ComingHere","${it.itemId} ${it.title}")
                })
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .requiredWidth(width = 360.dp)
                        .requiredHeight(height = 640.dp)
                        .background(color = Color.White)
                ) {




                    val localContext = LocalContext.current

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 13.dp,
                                y = 181.dp)
                            .requiredWidth(width = 146.dp)
                            .requiredHeight(height = 209.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, AddtoCartActivity::class.java)
                                )
                            }
                    )

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 220.dp, y = 172.dp)
                            .requiredWidth(width = 146.dp)
                            .requiredHeight(height = 211.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, AddtoCartActivity::class.java)
                                )
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 1.dp, y = 26.dp)
                            .requiredSize(size = 42.dp)
                            .clickable {
                                localContext.startActivity(
                                    Intent(
                                        localContext,
                                        UserProfileActivity::class.java
                                    )
                                )
                            }
                            .clip(shape = CircleShape)
                            .border(
                                border = BorderStroke(0.10000000149011612.dp, Color(0xffbdbdbd)),
                                shape = CircleShape
                            ))

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 219.dp,
                                y = 417.dp)
                            .requiredWidth(width = 147.dp)
                            .requiredHeight(height = 150.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, AddtoCartActivity::class.java)
                                )
                            }
                    )




                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 13.dp,
                                y = 181.dp)
                            .requiredWidth(width = 146.dp)
                            .requiredHeight(height = 209.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                    )

                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 220.dp,
                                y = 172.dp)
                            .requiredWidth(width = 146.dp)
                            .requiredHeight(height = 211.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.cs),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 30.dp,
                                y = 90.dp)
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 220.dp)
                            .clip(shape = RoundedCornerShape(15.dp)))
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 219.dp,
                                y = 417.dp)
                            .requiredWidth(width = 147.dp)
                            .requiredHeight(height = 150.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .background(color = Color.White)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.ss),
                        contentDescription = " ",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 30.dp,
                                y = 540.dp)
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 220.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pro),
                        contentDescription = " ",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 1.dp,
                                y = 26.dp)
                            .requiredSize(size = 42.dp)
                            .clip(shape = CircleShape)
                            .border(border = BorderStroke(0.10000000149011612.dp, Color(0xffbdbdbd)),
                                shape = CircleShape))
                    Image(
                        painter = painterResource(id = R.drawable.ts),
                        contentDescription = "",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 30.dp,
                                y = 240.dp)
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 220.dp)
                            .clip(shape = RoundedCornerShape(20.dp)))
                    Image(
                        painter = painterResource(id = R.drawable.fs),
                        contentDescription = " ",
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 30.dp,
                                y = 390.dp)
                            .requiredWidth(width = 340.dp)
                            .requiredHeight(height = 220.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                localContext.startActivity(
                                    Intent(localContext, AddtoCartActivity::class.java)
                                )
                            })
                }

            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}