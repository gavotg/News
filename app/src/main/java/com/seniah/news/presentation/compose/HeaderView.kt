package com.seniah.news.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import news.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HeaderView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(id = R.drawable.ic_launcher_foreground),
                    modifier = Modifier
                        .background(
                            color = colors.primary,
                            shape = CircleShape
                        )
                        .size(55.dp),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp),
                    text = stringResource(R.string.app_name),
                    color = colors.primary,
                    style = typography.h1,
                )
            }
            Text(
                text = SimpleDateFormat(
                    "d MMMM yyyy HH:mm", Locale.getDefault()
                ).format(Date())
                    .toString(),
                style = typography.subtitle1,
                color = colors.primary
            )
        }
    }
}

@Preview
@Composable
fun HeaderViewPreview() {
    HeaderView()
}
