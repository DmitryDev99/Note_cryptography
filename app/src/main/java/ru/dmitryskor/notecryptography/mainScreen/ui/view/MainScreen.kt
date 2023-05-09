package ru.dmitryskor.notecryptography.mainScreen.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.dmitryskor.notecryptography.MainAppBar
import ru.dmitryskor.notecryptography.mainScreen.ui.entity.NoteItemUI
import ru.dmitryskor.notecryptography.mainScreen.ui.viewModel.ListNoteScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListNoteScreen(
    navController: NavController
) {
    val viewModel = viewModel<ListNoteScreenViewModel>()
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            MainAppBar(
                title = {

                    Text(text = state.value.title)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(painter = painterResource(id = state.value.floatImage), state.value.floatContentDescription) {

            }
        }
    ) {
        ListNotes(it, emptyList()) {

        }
    }
}

@Composable
fun FloatingActionButton(painter: Painter, contentDescription: String? = null, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Icon(painter = painter, contentDescription = contentDescription)
    }
}

@Composable
fun ListNotes(padding: PaddingValues, listItem: List<NoteItemUI>, onClickItem: (Int) -> Unit) {
    LazyColumn(modifier = Modifier
        .padding(padding)
        .fillMaxSize()) {

    }
}
