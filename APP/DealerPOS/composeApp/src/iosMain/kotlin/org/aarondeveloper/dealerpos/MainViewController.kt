package org.aarondeveloper.dealerpos

import androidx.compose.ui.window.ComposeUIViewController
import org.aarondeveloper.dealerpos.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
)
{
    App()
}