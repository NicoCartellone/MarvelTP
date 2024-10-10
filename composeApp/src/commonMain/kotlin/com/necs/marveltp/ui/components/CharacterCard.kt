package com.necs.marveltp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.necs.marveltp.data.models.Character

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterCard(
    character: Character,
    onClick: () -> Unit
) {
    Card(
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(character.name)
            AsyncImage(
                model = character.thumbnailUrl,
                contentDescription = "Character image",
                contentScale = ContentScale.Crop
            )
        }
    }
}