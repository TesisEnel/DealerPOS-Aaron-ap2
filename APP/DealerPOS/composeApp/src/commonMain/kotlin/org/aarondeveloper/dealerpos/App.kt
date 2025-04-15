package org.aarondeveloper.dealerpos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.aarondeveloper.dealerpos.presentation.navigation.NavigationNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        val navHost = rememberNavController()
        NavigationNavHost(navHost)
    }
}