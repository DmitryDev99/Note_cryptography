package ru.dmitryskor.notecryptography

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.dmitryskor.notecryptography.core.ui.theme.NoteCryptographyTheme
import ru.dmitryskor.notecryptography.navigation.ui.NavScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            context.emit(this@MainActivity)
        }
        setContent {
            NoteCryptographyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavScreen()
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        lifecycleScope.launch {
            context.emit(this@MainActivity)
        }
    }

    companion object {
        private val context = MutableSharedFlow<Context?>(replay = 1, extraBufferCapacity = 1)
        val contextFlow = context.asSharedFlow()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            MainAppBar(
                title = {
                    Button(onClick = {

                    }) {

                    }
                }
            )
        }
    ) {
        TextFieldFullScreen(name, Modifier.padding(it)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteCryptographyTheme {
        Greeting("Android")
    }
}

@ExperimentalMaterial3Api
@Composable
fun TextFieldFullScreen(value: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    title: @Composable () -> Unit
) {
    TopAppBar(
        title = title
    )
}