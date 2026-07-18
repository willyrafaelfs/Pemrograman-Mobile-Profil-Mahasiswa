package com.example.profilmahasiswa.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilmahasiswa.ui.theme.ProfilMahasiswaTheme

/**
 * ProfileEdit - Screen untuk mengedit data profil mahasiswa.
 * Menangani edit: Email, Telepon, Alamat, dan Hobby.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEdit(
    onBack: () -> Unit = {},
    initialNim: String = "",
    initialEmail: String = "",
    initialTelepon: String = "",
    initialAlamat: String = "",
    initialHobby: String = "",
    onSave: (String, String, String, String, String) -> Unit = { _, _, _, _, _ -> }
) {
    // State data (Sinkron dengan input awal)
    var nim by remember { mutableStateOf(initialNim) }
    var email by remember { mutableStateOf(initialEmail) }
    var telepon by remember { mutableStateOf(initialTelepon) }
    var alamat by remember { mutableStateOf(initialAlamat) }
    var hobby by remember { mutableStateOf(initialHobby) }

    // State untuk mengontrol mode edit (enabled/disabled)
    var isEditing by remember { mutableStateOf(false) }

    // State sementara untuk menampung input saat sedang mengedit
    var tempNim by remember { mutableStateOf(nim) }
    var tempEmail by remember { mutableStateOf(email) }
    var tempTelepon by remember { mutableStateOf(telepon) }
    var tempAlamat by remember { mutableStateOf(alamat) }
    var tempHobby by remember { mutableStateOf(hobby) }

    // Logika untuk mengecek apakah ada perubahan data
    val hasChanges = tempNim != nim || tempEmail != email || tempTelepon != telepon || 
                     tempAlamat != alamat || tempHobby != hobby

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Profil", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Perbarui informasi kontak dan hobi Anda.",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // TextFields untuk input data
            EditField(
                label = "NIM",
                value = tempNim,
                onValueChange = { tempNim = it },
                icon = Icons.Default.Badge,
                enabled = isEditing
            )

            EditField(
                label = "Email",
                value = tempEmail,
                onValueChange = { tempEmail = it },
                icon = Icons.Default.Email,
                enabled = isEditing
            )

            EditField(
                label = "Telepon",
                value = tempTelepon,
                onValueChange = { tempTelepon = it },
                icon = Icons.Default.Phone,
                enabled = isEditing
            )

            EditField(
                label = "Alamat",
                value = tempAlamat,
                onValueChange = { tempAlamat = it },
                icon = Icons.Default.LocationOn,
                enabled = isEditing
            )

            EditField(
                label = "Hobby",
                value = tempHobby,
                onValueChange = { tempHobby = it },
                icon = Icons.Default.Star,
                enabled = isEditing
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Aksi: Edit Profil / Simpan / Batal Edit
            Button(
                onClick = {
                    if (isEditing) {
                        if (hasChanges) {
                            // Update state lokal
                            nim = tempNim
                            email = tempEmail
                            telepon = tempTelepon
                            alamat = tempAlamat
                            hobby = tempHobby
                            // Kirim data ke parent (MainActivity) agar sinkron ke ProfileScreen
                            onSave(nim, email, telepon, alamat, hobby)
                            isEditing = false
                        } else {
                            isEditing = false
                        }
                    } else {
                        isEditing = true
                        tempNim = nim
                        tempEmail = email
                        tempTelepon = telepon
                        tempAlamat = alamat
                        tempHobby = hobby
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = when {
                        isEditing && hasChanges -> Color(0xFF2E7D32) // Hijau untuk Simpan
                        isEditing && !hasChanges -> MaterialTheme.colorScheme.error // Merah untuk Batal
                        else -> MaterialTheme.colorScheme.primary // Default untuk Edit Profil
                    }
                )
            ) {
                Icon(
                    imageVector = when {
                        !isEditing -> Icons.Default.Edit
                        hasChanges -> Icons.Default.Save
                        else -> Icons.Default.Close
                    },
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when {
                        !isEditing -> "Edit Profil"
                        hasChanges -> "Simpan"
                        else -> "Batal Edit"
                    },
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * Komponen Reusable untuk TextField di halaman edit
 */
@Composable
fun EditField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    enabled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileEditPreview() {
    ProfilMahasiswaTheme {
        ProfileEdit()
    }
}
