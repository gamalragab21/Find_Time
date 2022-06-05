package com.example.findtime.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.res.stringResource
import com.raywenderlich.compose.ui.MainView
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1
        Napier.base(DebugAntilog())
        setContent {

// 2
            MainView {
// 3
                TopAppBar(title = {
// 4
                    when (it) {
                        0 -> Text(text =
                        stringResource(R.string.world_clocks))
                        else -> Text(text =
                        stringResource(R.string.findmeeting))
                    }
                })

            }
        }
    }
}
