package com.necs.marveltp.ui.screens.characterDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.necs.marveltp.data.models.Character
import com.necs.marveltp.ui.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    vm: CharacterDetailViewModel,
    onBack: () -> Unit,
) {
    val state = vm.state
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(topBar = {
            CharacterDetailTopBar(
                title = state.character?.name ?: "",
                onBack = onBack,
                scrollBehavior = scrollBehavior
            )
        }) { padding ->
            if (state.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.character?.let { character ->
                CharacterDetail(
                    modifier = Modifier.padding(padding),
                    character = character
                )
            }
        }
    }
}

@Composable
private fun CharacterDetail(
    modifier: Modifier = Modifier,
    character: Character,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = character.thumbnailUrl,
            contentDescription = "Character image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().aspectRatio(16 / 9f)
        )
        Text(
            text = character.description,
            modifier = Modifier.padding(16.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterDetailTopBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(title = { Text(title) }, navigationIcon = {
        IconButton(onClick = { onBack() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back"
            )
        }
    }, scrollBehavior = scrollBehavior
    )
}