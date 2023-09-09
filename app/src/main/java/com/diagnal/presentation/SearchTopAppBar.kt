package com.diagnal.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.diagnal.R
import com.diagnal.ui.theme.LightGray80
import com.diagnal.ui.theme.titilliumWebFont
/**
 * search toolbar
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchTopAppBar(onBackClick: () -> Unit, onSearchTextChange: (String) -> Unit,searchTextState: MutableState<String>) {
    var searchText = remember { searchTextState }

    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Black,
            navigationIconContentColor = Color.White,
            titleContentColor = LightGray80,
            actionIconContentColor = Color.White,
        ),
        title = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 18.dp, 0.dp)) {
                val textStyle = LocalTextStyle.current
                val textFieldColors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = LightGray80,
                    textColor = LightGray80,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    focusedTrailingIconColor = Color.White,
                    unfocusedTrailingIconColor = Color.White,
                    disabledTrailingIconColor = Color.White,
                    placeholderColor = LightGray80,
                )
                BasicTextField(
                    value = searchText.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp),
                    onValueChange = { newText ->
                        onSearchTextChange(newText)
                        searchText.value = newText
                    },
                    textStyle = textStyle.merge(TextStyle(color = LightGray80))
                        .merge(TextStyle(fontFamily = titilliumWebFont))
                        .merge(TextStyle(fontWeight = FontWeight.Normal)),
                    cursorBrush = SolidColor(Color.White),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        TextFieldDefaults.TextFieldDecorationBox(
                            value = searchText.value,
                            visualTransformation = VisualTransformation.None,
                            innerTextField = innerTextField,
                            placeholder = { Text(text = "Search Movies") },
                            label = null,
                            leadingIcon = null,
                            trailingIcon = null,
                            supportingText = null,
                            shape = TextFieldDefaults.filledShape,
                            singleLine = true,
                            enabled = true,
                            isError = false,
                            interactionSource = remember { MutableInteractionSource() },
                            colors = textFieldColors,
                            contentPadding = PaddingValues(0.dp,0.dp,24.dp,0.dp),
                        )
                    }
                )
                Box(modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.search_cancel),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp, 2.dp, 0.dp, 2.dp)
                    )
                }
            }
        }
    )
}