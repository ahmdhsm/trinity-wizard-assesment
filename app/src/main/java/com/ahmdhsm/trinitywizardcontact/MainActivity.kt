package com.ahmdhsm.trinitywizardcontact

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahmdhsm.trinitywizardcontact.core.AppRoutes
import com.ahmdhsm.trinitywizardcontact.screens.DetailContactScreen
import com.ahmdhsm.trinitywizardcontact.screens.ListContactScreen
import com.ahmdhsm.trinitywizardcontact.ui.theme.TrinityWizardContactTheme
import com.ahmdhsm.trinitywizardcontact.viewmodels.ListContactViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this

        setContent {
            TrinityWizardContactTheme {

                val viewModel = ListContactViewModel(context)

                viewModel.getContactFromAsset()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AppRoutes.LIST_CONTACT_SCREEN
                    ) {
                        composable(AppRoutes.LIST_CONTACT_SCREEN) {
                            ListContactScreen(navigation = navController, viewModel)
                        }
                        composable(AppRoutes.DETAIL_CONTACT_SCREEN + "{contactId}") { backStackEntry ->
                            val contactId = backStackEntry.arguments?.getString("contactId")

                            val contact = contactId?.let { viewModel.getContactById(it) }
                            DetailContactScreen(
                                navController,
                                viewModel,
                                contact
                            )
                        }
                    }
                }
            }
        }
    }
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
    TrinityWizardContactTheme {
        Greeting("Android")
    }
}