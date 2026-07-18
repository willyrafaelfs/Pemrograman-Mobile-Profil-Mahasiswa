package com.example.profilmahasiswa.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// =============================================================
// WARNA KUSTOM
// Definisikan warna aplikasi di satu tempat agar konsisten.
// Material 3 menggunakan sistem "color roles" untuk theming.
// =============================================================

// Warna utama (Primary)
val md_theme_light_primary = Color(0xFF1565C0)          // Biru
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFD1E4FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001D36)

// Warna sekunder (Secondary)
val md_theme_light_secondary = Color(0xFF00897B)         // Teal
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFB2DFDB)
val md_theme_light_onSecondaryContainer = Color(0xFF00201E)

// Warna tersier (Tertiary)
val md_theme_light_tertiary = Color(0xFF7B1FA2)          // Ungu
val md_theme_light_onTertiary = Color(0xFFFFFFFF)

// Background & Surface
val md_theme_light_background = Color(0xFFFAFAFA)
val md_theme_light_surface = Color(0xFFFFFFFF)
val md_theme_light_surfaceVariant = Color(0xFFF0F0F0)

// Error
val md_theme_light_error = Color(0xFFD32F2F)

// Dark Theme Colors
val md_theme_dark_primary = Color(0xFF90CAF9)
val md_theme_dark_onPrimary = Color(0xFF0D47A1)
val md_theme_dark_primaryContainer = Color(0xFF1565C0)
val md_theme_dark_onPrimaryContainer = Color(0xFFD1E4FF)

val md_theme_dark_secondary = Color(0xFF80CBC4)
val md_theme_dark_secondaryContainer = Color(0xFF00695C)
val md_theme_dark_onSecondaryContainer = Color(0xFFB2DFDB)

val md_theme_dark_tertiary = Color(0xFFCE93D8)

val md_theme_dark_background = Color(0xFF121212)
val md_theme_dark_surface = Color(0xFF1E1E1E)
val md_theme_dark_surfaceVariant = Color(0xFF2C2C2C)

// =============================================================
// COLOR SCHEMES
// ColorScheme mendefinisikan semua "color roles" Material 3.
// Compose otomatis menggunakan warna yang sesuai di seluruh UI.
// =============================================================

private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    background = md_theme_light_background,
    surface = md_theme_light_surface,
    surfaceVariant = md_theme_light_surfaceVariant,
    error = md_theme_light_error,
)

private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    background = md_theme_dark_background,
    surface = md_theme_dark_surface,
    surfaceVariant = md_theme_dark_surfaceVariant,
)

// =============================================================
// THEME COMPOSABLE
// Fungsi utama untuk menerapkan tema ke seluruh aplikasi.
// Dipanggil di MainActivity: ProfilMahasiswaTheme { ... }
// =============================================================

@Composable
fun ProfilMahasiswaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),   // Mengikuti setting sistem
    dynamicColor: Boolean = true,                  // Dynamic Color (Android 12+)
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Dynamic Color mengambil warna dari wallpaper pengguna (Android 12+)
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),       // Menggunakan default Material 3 typography
        content = content
    )
}
