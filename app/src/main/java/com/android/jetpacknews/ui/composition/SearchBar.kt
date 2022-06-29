package com.android.jetpacknews.ui.composition

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchInput: String = "",
    onSearchInputChanged: (String) -> Unit,
    onClearSearchClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(Dp.Hairline, MaterialTheme.colors.onSurface.copy(alpha = .6f)),
        elevation = 4.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                val keyboardController = LocalSoftwareKeyboardController.current
                TextField(
                    value = searchInput,
                    onValueChange = { onSearchInputChanged(it) },
                    placeholder = { Text(stringResource(R.string.search_hint)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ), // keyboardOptions change the newline key to a search key on the soft keyboard
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    // keyboardActions submits the search query when the search key is pressed
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            onSearchInputChanged("")
                            keyboardController?.hide()
                        }
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                if (searchInput.isNotEmpty()) {
                    IconButton(onClick = onClearSearchClick) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Close search"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
