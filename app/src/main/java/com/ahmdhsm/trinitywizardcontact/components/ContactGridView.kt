package com.ahmdhsm.trinitywizardcontact.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmdhsm.trinitywizardcontact.models.ContactModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun contactGridView(context: Context) {
    // on below line we are creating and initializing our array list
    lateinit var contact: List<ContactModel>
    contact = ArrayList<ContactModel>()

    // on below line we are adding data to our list.
    contact = contact + ContactModel(id = "11", firstName = "Name", lastName = "")
    contact = contact + ContactModel(id = "11", firstName = "Name", lastName = "")
    contact = contact + ContactModel(id = "11", firstName = "Name", lastName = "")
    contact = contact + ContactModel(id = "11", firstName = "Name", lastName = "")
    contact = contact + ContactModel(id = "11", firstName = "Name", lastName = "")
//    courseList = courseList + GridModal("JavaScript", R.drawable.js)
//    courseList = courseList + GridModal("Python", R.drawable.python)
//    courseList = courseList + GridModal("C++", R.drawable.c)
//    courseList = courseList + GridModal("C#", R.drawable.csharp)
//    courseList = courseList + GridModal("Java", R.drawable.java)
//    courseList = courseList + GridModal("Node Js", R.drawable.nodejs)

    // on below line we are adding lazy
    // vertical grid for creating a grid view.
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
//        // on below line we are setting the
//        // column count for our grid view.
//        cells = GridCells.Fixed(2),
//        // on below line we are adding padding
//        // from all sides to our grid view.
//        modifier = Modifier.padding(10.dp)

    ) {
        // on below line we are displaying our
        // items upto the size of the list.
        items(contact.size) {
            // on below line we are creating a
            // card for each item of our grid view.
            Card(

                onClick = {

                },


                modifier = Modifier
                    .padding(8.dp)


                // on below line we are adding elevation for the card.
//                elevation = 6.dp
            ) {
                // on below line we are creating a column on below sides.
                Column(
                    // on below line we are adding padding
                    // padding for our column and filling the max size.
                    Modifier
                        .fillMaxSize()
                        .padding(5.dp),

                    // on below line we are adding
                    // horizontal alignment for our column
                    horizontalAlignment = Alignment.CenterHorizontally,

                    // on below line we are adding
                    // vertical arrangement for our column
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Contact Icon",
                        Modifier.padding(bottom = 4.dp, top = 10.dp)
                    )

                    Text(
                        text = contact[it].firstName,
                        modifier = Modifier.padding(top = 4.dp, bottom = 10.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}