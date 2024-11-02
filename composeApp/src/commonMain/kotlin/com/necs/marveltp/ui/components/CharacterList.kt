package com.necs.marveltp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.necs.marveltp.data.models.Character

@Composable
fun CharacterList(onClick: (Character) -> Unit, modifier: Modifier, characters: List<Character>) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(characters.size) {
            CharacterItem(
                character = characters[it],
                onCharacterClick = { onClick(characters[it]) }
            )
        }
    }
}
