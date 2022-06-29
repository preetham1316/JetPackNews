package com.android.jetpacknews.ui.composition

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.android.jetpacknews.R

@Composable
fun AppBar(onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(
                    onClick = { onBackPressed() },
                    modifier = Modifier
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back",
                        tint = Color.Unspecified
                    )
                }
            }
        }
    )
}
