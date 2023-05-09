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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.dmitryskor.notecryptography.mainScreen.domain.services.StringResListNoteService
import ru.dmitryskor.notecryptography.navigation.ui.NavScreen
import ru.dmitryskor.notecryptography.ui.theme.NoteCryptographyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StringService.initContext(this)
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
        StringService.initContext(this)
    }
}

object StringService : StringResListNoteService {
    private const val EMPTY_STRING = ""
    fun initContext(context: Context) {
        listNotesTitle_Private.value = context.getString(R.string.list_note_title)
        listNotesNewNote_Private.value = context.getString(R.string.list_notes_new_note)
    }

    private val listNotesTitle_Private = MutableStateFlow(EMPTY_STRING)
    override val listNotesTitle = listNotesTitle_Private.asStateFlow()
    private val listNotesNewNote_Private = MutableStateFlow(EMPTY_STRING)
    override val listNotesNewNote = listNotesNewNote_Private.asStateFlow()
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