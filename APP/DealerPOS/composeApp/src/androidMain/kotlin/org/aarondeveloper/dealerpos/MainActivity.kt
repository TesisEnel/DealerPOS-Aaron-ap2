package org.aarondeveloper.dealerpos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import org.aarondeveloper.dealerpos.presentation.screens.informacion.InformacionBodyScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
    InformacionBodyScreen(
        uiState = TODO(),
        onNavBackClick = {},
        onCerrarModalClick = {},
    )
}