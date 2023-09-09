package com.diagnal.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.diagnal.R
import com.diagnal.ui.theme.LightGray80
import com.diagnal.ui.theme.titilliumWebFont
/**
 * material app bar
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun normalTopAppBar(onSearchClick: () -> Unit, onBackIconPress : () -> Unit){
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.White,
            titleContentColor = LightGray80,
            actionIconContentColor = Color.White,
        ),
        title = { Text("Romantic Comedy", fontFamily = titilliumWebFont , style = MaterialTheme.typography.titleLarge) },
        navigationIcon = {
            IconButton(onClick = onBackIconPress) {
                Icon(
                    painter = painterResource(R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier.size(16.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    )
}