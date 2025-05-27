package info.imtushar.expensetracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import info.imtushar.expensetracker.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val sourceSansFontFamily = FontFamily(
    Font(googleFont = GoogleFont("Source Sans 3"), fontProvider = provider)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
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

val appbarTitle = TextStyle(
    fontFamily = sourceSansFontFamily,
    fontSize = 34.sp,
    lineHeight = 41.sp,
    fontWeight = FontWeight(700),
    color = Color.White,
    letterSpacing = 0.37.sp,
)

val regularBody = TextStyle(
    fontSize = 17.sp,
    lineHeight = 22.sp,
    fontFamily = sourceSansFontFamily,
    fontWeight = FontWeight(400),
)

val regularTitle2 = TextStyle(
    fontSize = 22.sp,
    lineHeight = 28.sp,
    fontFamily = sourceSansFontFamily,
    fontWeight = FontWeight(400),
    letterSpacing = 0.35.sp,
)

val largeTitle =  TextStyle(
    fontSize = 34.sp,
    lineHeight = 41.sp,
    fontFamily = sourceSansFontFamily,
    fontWeight = FontWeight(400),
    letterSpacing = 0.37.sp,
)

val regularHeadline =  TextStyle(
    fontSize = 17.sp,
    lineHeight = 22.sp,
    fontFamily = sourceSansFontFamily,
    fontWeight = FontWeight(600),
    color = Color(0x99EBEBF5),
    )



