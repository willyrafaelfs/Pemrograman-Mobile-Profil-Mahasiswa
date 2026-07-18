package com.example.profilmahasiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.profilmahasiswa.screens.ProfileScreen
import com.example.profilmahasiswa.screens.DataNilaiScreen
import com.example.profilmahasiswa.ui.theme.ProfilMahasiswaTheme

/**
 * MainActivity - Entry point aplikasi.
 *
 * Di Jetpack Compose, kita tidak lagi menggunakan XML layout.
 * Semua UI didefinisikan sebagai fungsi @Composable di dalam setContent { }.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Menggunakan full screen edge-to-edge

        setContent {
            // State untuk Dark Mode
            var isDarkMode by remember { mutableStateOf(false) }
            // State untuk Navigasi sederhana
            var currentScreen by remember { mutableStateOf("profile") }

            // State Data Profil (Single Source of Truth)
            var nim by remember { mutableStateOf("2083000168") }
            var email by remember { mutableStateOf("23083000168@student.unmer.ac.id") }
            var telepon by remember { mutableStateOf("+62 822-6703-9811") }
            var alamat by remember { mutableStateOf("Malang, Jawa Timur") }
            var hobby by remember { mutableStateOf("Coding, Gaming, Music") }

            // Theme membungkus seluruh UI agar konsisten
            ProfilMahasiswaTheme(darkTheme = isDarkMode) {
                // Navigasi sederhana antar screen
                when (currentScreen) {
                    "profile" -> ProfileScreen(
                        isDarkMode = isDarkMode,
                        onDarkModeChange = { isDarkMode = it },
                        onNavigateToEdit = { currentScreen = "edit" },
                        onNavigateToStats = { currentScreen = "stats" }, // Callback baru
                        nim = nim,
                        email = email,
                        telepon = telepon,
                        alamat = alamat,
                        hobby = hobby
                    )
                    "edit" -> com.example.profilmahasiswa.screens.ProfileEdit(
                        onBack = { currentScreen = "profile" },
                        initialNim = nim,
                        initialEmail = email,
                        initialTelepon = telepon,
                        initialAlamat = alamat,
                        initialHobby = hobby,
                        onSave = { newNim, newEmail, newTelepon, newAlamat, newHobby ->
                            nim = newNim
                            email = newEmail
                            telepon = newTelepon
                            alamat = newAlamat
                            hobby = newHobby
                        }
                    )
                    "stats" -> DataNilaiScreen(
                        onBack = { currentScreen = "profile" }
                    )
                }
            }
        }
    }
}
