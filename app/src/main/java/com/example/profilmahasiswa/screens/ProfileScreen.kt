package com.example.profilmahasiswa.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilmahasiswa.R
import com.example.profilmahasiswa.ui.theme.ProfilMahasiswaTheme
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.MusicNote

// =============================================================
// PROFIL MAHASISWA SCREEN
// Demonstrasi: Column, Row, Box, Modifier, State, Card, Button
// =============================================================

/**
 * ProfileScreen - Halaman utama profil mahasiswa.
 *
 * Konsep yang dipelajari:
 * 1. Scaffold - Layout dasar dengan TopAppBar
 * 2. Column - Menyusun elemen secara vertikal
 * 3. Row - Menyusun elemen secara horizontal
 * 4. Box - Menumpuk elemen (overlapping)
 * 5. Modifier - Styling (padding, background, border, size)
 * 6. State - remember + mutableStateOf untuk data yang berubah
 * 7. Card - Material 3 card component
 * 8. Button - Interaksi pengguna
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onNavigateToEdit: () -> Unit,
    onNavigateToStats: () -> Unit,
    nim: String,
    email: String,
    telepon: String,
    alamat: String,
    hobby: String
) {
    // ============================================
    // STATE MANAGEMENT
    // ============================================
    var editCount by remember { mutableStateOf(0) }

    // Data profil statis (Nama tetap di sini atau bisa dipindah juga)
    var nama by remember { mutableStateOf("Willy Rafael F. Silalahi") }
    var jurusan by remember { mutableStateOf("Sistem Informasi") }

    // Scaffold menyediakan struktur layout standar Material 3
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Profil Mahasiswa",
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    // Tombol Toggle Dark Mode
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = onDarkModeChange
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        // ============================================
        // COLUMN - Layout Vertikal
        // ============================================
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            ProfilePhotoSection()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = nama,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "NIM: $nim",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = "Jurusan",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = jurusan,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            ContactInfoCard(email = email, telepon = telepon, alamat = alamat)

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.clickable { onNavigateToStats() }) {
                AcademicStatsCard()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tambahkan Hobby section
            HobbyCard(hobby = hobby)

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Edit - Navigasi ke ProfileEdit
            Button(
                onClick = onNavigateToEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Edit Profil",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Tombol Share Profil
            OutlinedButton(
                onClick = { /* Action share */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Share Profil",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tombol diklik $editCount kali",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// =============================================================
// COMPOSABLE COMPONENTS (Reusable)
// Setiap section dibuat sebagai fungsi @Composable terpisah
// agar kode lebih rapi dan bisa di-reuse.
// =============================================================

/**
 * ProfilePhotoSection - Foto profil dengan Box (overlapping).
 *
 * Box digunakan untuk menumpuk:
 * 1. Foto profil (background)
 * 2. Badge status (di atas foto, pojok kanan bawah)
 */
@Composable
fun ProfilePhotoSection() {
    Box(
        contentAlignment = Alignment.Center
    ) {
        // Foto profil menggunakan Image dari drawable
        Image(
            painter = painterResource(id = R.drawable.cv_pp), // Ganti dengan R.drawable.foto_anda
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                )
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
        )

        // Badge status
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(28.dp)
                .clip(CircleShape)
                .background(Color(0xFF4CAF50))
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Aktif",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * ContactInfoCard - Card berisi informasi kontak.
 *
 * Demonstrasi:
 * - Card component dengan elevation
 * - Column di dalam Card
 * - Row untuk setiap baris info (icon + teks)
 * - Modifier: fillMaxWidth, padding, border
 */
@Composable
fun ContactInfoCard(email: String, telepon: String, alamat: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.ContactPage,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Informasi Kontak",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ContactRow(
                icon = Icons.Default.Email,
                label = "Email",
                value = email
            )

            Spacer(modifier = Modifier.height(12.dp))

            ContactRow(
                icon = Icons.Default.Phone,
                label = "Telepon",
                value = telepon
            )

            Spacer(modifier = Modifier.height(12.dp))

            ContactRow(
                icon = Icons.Default.LocationOn,
                label = "Alamat",
                value = alamat
            )
        }
    }
}

/**
 * ContactRow - Satu baris informasi kontak.
 *
 * Menggunakan Row untuk menyusun icon, label, dan value secara horizontal.
 * Ini adalah contoh Composable yang reusable dengan parameter.
 *
 * @param icon Icon yang ditampilkan di kiri
 * @param label Judul field (contoh: "Email")
 * @param value Isi field (contoh: "ahmad@mail.com")
 */
@Composable
fun ContactRow(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon di dalam lingkaran berwarna
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Label dan value di dalam Column (vertikal)
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

/**
 * AcademicStatsCard - Card berisi statistik akademik.
 *
 * Demonstrasi:
 * - Row dengan Modifier.weight() untuk distribusi ruang yang rata
 * - Column di dalam Row
 * - Box untuk elemen dekoratif
 */
@Composable
fun AcademicStatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Assessment,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Statistik Akademik",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Row dengan 3 kolom menggunakan weight()
            // weight() membagi ruang secara proporsional
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatItem(
                    modifier = Modifier.weight(1f),    // 1/3 dari lebar
                    value = "3.75",
                    label = "IPK",
                    color = MaterialTheme.colorScheme.primary
                )
                StatItem(
                    modifier = Modifier.weight(1f),    // 1/3 dari lebar
                    value = "120",
                    label = "SKS",
                    color = MaterialTheme.colorScheme.tertiary
                )
                StatItem(
                    modifier = Modifier.weight(1f),    // 1/3 dari lebar
                    value = "6",
                    label = "Semester",
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

/**
 * StatItem - Satu item statistik (angka + label).
 *
 * @param modifier Modifier dari parent (termasuk weight)
 * @param value Angka yang ditampilkan besar
 * @param label Keterangan di bawah angka
 * @param color Warna aksen
 */
@Composable
fun StatItem(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    color: Color
) {
    Column(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * HobbyCard - Card untuk menampilkan daftar hobby.
 */
@Composable
fun HobbyCard(hobby: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Hobby",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = hobby,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            )
        }
    }
}

@Composable
fun HobbyItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.Medium)
    }
}

// =============================================================
// PREVIEW
// =============================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Profil Mahasiswa - Light Mode"
)
@Composable
fun ProfileScreenPreview() {
    var isDark by remember { mutableStateOf(false) }
    ProfilMahasiswaTheme(darkTheme = isDark) {
        ProfileScreen(
            isDarkMode = isDark,
            onDarkModeChange = { isDark = it },
            onNavigateToEdit = {},
            onNavigateToStats = {},
            nim = "2083000168",
            email = "23083000168@student.unmer.ac.id",
            telepon = "+62 822-6703-9811",
            alamat = "Malang, Jawa Timur",
            hobby = "Coding, Gaming, Music"
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Profil Mahasiswa - Dark Mode"
)
@Composable
fun ProfileScreenDarkPreview() {
    var isDark by remember { mutableStateOf(true) }
    ProfilMahasiswaTheme(darkTheme = isDark) {
        ProfileScreen(
            isDarkMode = isDark,
            onDarkModeChange = { isDark = it },
            onNavigateToEdit = {},
            onNavigateToStats = {},
            nim = "2083000168",
            email = "23083000168@student.unmer.ac.id",
            telepon = "+62 822-6703-9811",
            alamat = "Malang, Jawa Timur",
            hobby = "Coding, Gaming, Music"
        )
    }
}

// Preview untuk komponen individual
@Preview(showBackground = true, name = "Contact Card")
@Composable
fun ContactInfoCardPreview() {
    ProfilMahasiswaTheme {
        ContactInfoCard(
            email = "23083000168@student.unmer.ac.id",
            telepon = "+62 822-6703-9811",
            alamat = "Malang, Jawa Timur",
        )
    }
}

@Preview(showBackground = true, name = "Academic Stats Card")
@Composable
fun AcademicStatsCardPreview() {
    ProfilMahasiswaTheme {
        AcademicStatsCard()
    }
}
