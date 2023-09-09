package com.diagnal.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.diagnal.R
val titilliumWebFont = FontFamily(
    Font(R.font.titilliumweb_extralight, weight = FontWeight.ExtraLight),
    Font(R.font.titilliumweb_light, weight = FontWeight.Light),
    Font(R.font.titilliumweb_regular),
    Font(R.font.titilliumweb_semibold, weight = FontWeight.SemiBold),
    Font(R.font.titilliumweb_bold, weight = FontWeight.Bold),
    Font(R.font.titilliumweb_black, weight = FontWeight.Black),

    Font(R.font.titilliumweb_italic, style = FontStyle.Italic),
    Font(R.font.titilliumweb_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = titilliumWebFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)