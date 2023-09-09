package com.diagnal.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Int.pixToDp(): Dp{
    val density: Float = LocalDensity.current.density
    return (this.div(density)).dp
}