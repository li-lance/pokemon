package com.seraphim.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val viewModel by inject<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

//       edgeToEdgeWindowInsetsControllerCompat()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        viewModel.getList()
        setContent {

        }
    }
}