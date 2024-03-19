package com.felpster.loginapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.felpster.loginapp.R
import com.felpster.loginapp.ui.components.ErrorLayoutTags.ERROR_LAYOUT

@Composable
fun ErrorLayout(
    message: String?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.testTag(ERROR_LAYOUT),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.sad_face),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Black),
            text = message ?: stringResource(id = R.string.generic_error_message),
        )
    }
}

object ErrorLayoutTags {
    const val ERROR_LAYOUT = "ErrorLayoutTags_content"
}