package com.necs.marveltp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CharacterSearchBar(
    modifier: Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Buscar personajes...") },
        )
    }
}
