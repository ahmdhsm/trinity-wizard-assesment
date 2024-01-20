package com.ahmdhsm.trinitywizardcontact.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmdhsm.trinitywizardcontact.core.AppRoutes
import com.ahmdhsm.trinitywizardcontact.ui.theme.mainColor
import com.ahmdhsm.trinitywizardcontact.viewmodels.ListContactViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListContactScreen(navigation: NavController, viewModel: ListContactViewModel) {
    var refreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(refreshing, {
        refreshing = true
        viewModel.getContactFromAsset()
        refreshing = false
    })

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                    Text("Contacts")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigation.navigate(AppRoutes.DETAIL_CONTACT_SCREEN)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Contact",
                            tint = mainColor
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navigation.navigate(AppRoutes.DETAIL_CONTACT_SCREEN)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add Contact",
                            tint = mainColor
                        )
                    }
                },
            )
        },
    ) { innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .pullRefresh(pullRefreshState)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(viewModel.listContact.count()) {

                    Card(

                        onClick = {

                            navigation.navigate(AppRoutes.DETAIL_CONTACT_SCREEN + viewModel.listContact[it].id)

                        },


                        modifier = Modifier
                            .padding(8.dp)

                    ) {

                        Column(

                            Modifier
                                .fillMaxSize()
                                .padding(5.dp),

                            horizontalAlignment = Alignment.CenterHorizontally,

                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Contact Icon",
                                Modifier.padding(bottom = 4.dp, top = 10.dp)
                            )

                            Text(
                                text = viewModel.listContact[it].firstName + " " + viewModel.listContact[it].lastName,
                                modifier = Modifier.padding(top = 4.dp, bottom = 10.dp),
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
            PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}