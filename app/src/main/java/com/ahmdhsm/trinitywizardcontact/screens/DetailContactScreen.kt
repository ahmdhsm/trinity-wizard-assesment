package com.ahmdhsm.trinitywizardcontact.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmdhsm.trinitywizardcontact.models.ContactModel
import com.ahmdhsm.trinitywizardcontact.ui.theme.mainColor
import com.ahmdhsm.trinitywizardcontact.viewmodels.ListContactViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContactScreen(
    navigation: NavController,
    viewModel: ListContactViewModel,
    contact: ContactModel?
) {
    val focusManager = LocalFocusManager.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    if (contact != null) {
        firstName = contact.firstName
        lastName = contact.lastName
        if (contact.phone != null) {
            phone = contact.phone!!
        }
        if (contact.email != null) {
            email = contact.email!!
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                    Text("")
                },
                actions = {
                    TextButton(onClick = {
                        if (firstName != "" && lastName != "") {
                            if (contact == null) {
                                viewModel.addContact(
                                    ContactModel(
                                        id = LocalDate.now().toString(),
                                        firstName = firstName,
                                        lastName = lastName,
                                    )
                                )
                            } else {
                                viewModel.updateContact(
                                    contact.id,
                                    ContactModel(
                                        id = "",
                                        firstName = firstName,
                                        lastName = lastName,
                                        phone = phone,
                                        email = email,
                                    )
                                )
                            }
                            navigation.popBackStack()
                        }

                    }) {
                        Text("Save", color = mainColor)
                    }
                },
                navigationIcon = {
                    TextButton(onClick = {
                        navigation.popBackStack()
                    }) {
                        Text("Cancel", color = mainColor)
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Contact Icon",
                Modifier
                    .size(200.dp)
                    .padding(10.dp)
            )
            Text(text = "Main Information")
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                value = firstName,
                onValueChange = {
                    firstName = it
                },
                label = { Text("First Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                value = lastName,
                onValueChange = {
                    lastName = it
                },
                label = { Text("Last Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
            )
            Text(text = "Sub Information")
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                value = phone,
                onValueChange = {
                    phone = it
                },
                label = { Text("Phone") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
            )
        }
    }
}