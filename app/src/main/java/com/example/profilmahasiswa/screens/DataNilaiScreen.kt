package com.example.profilmahasiswa.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profilmahasiswa.ui.theme.ProfilMahasiswaTheme

/**
 * DataNilaiScreen - Screen untuk menampilkan statistik akademik dan detail nilai.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataNilaiScreen(
    onBack: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Data Nilai", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
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
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)) // Background kontainer sedikit kontras
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Tabel Identitas Mahasiswa
            IdentityTable(
                nim = "2083000168",
                nama = "Willy Rafael F. Silalahi",
                semester = "6"
            )

            // Tabel Mata Kuliah dan Nilai
            GradesTable(
                listOf(
                    GradeEntry("MK001", "Pemrograman Mobile", 90, "A"),
                    GradeEntry("MK002", "Basis Data", 85, "A-"),
                    GradeEntry("MK003", "Analisis Desain Sistem", 78, "B+"),
                    GradeEntry("MK004", "Kecerdasan Buatan", 92, "A"),
                    GradeEntry("MK005", "Jaringan Komputer", 75, "B")
                )
            )

            // Tabel IP Sementara
            IPSummaryTable(ipk = "3.75", totalSks = "120")
        }
    }
}

@Composable
fun IdentityTable(nim: String, nama: String, semester: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IdentityItem(label = "NIM", value = nim, modifier = Modifier.weight(1.2f))
            VerticalDivider(modifier = Modifier.height(32.dp), thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            IdentityItem(label = "Nama", value = nama, modifier = Modifier.weight(2f))
            VerticalDivider(modifier = Modifier.height(32.dp), thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            IdentityItem(label = "Semester", value = semester, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun IdentityItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

data class GradeEntry(val kode: String, val matkul: String, val skor: Int, val nilai: String)

@Composable
fun GradesTable(grades: List<GradeEntry>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text("Kode", modifier = Modifier.weight(1f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("Mata Kuliah", modifier = Modifier.weight(2.2f), fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("Skor", modifier = Modifier.weight(0.8f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text("Nilai", modifier = Modifier.weight(0.8f), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            grades.forEachIndexed { index, entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(entry.kode, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                    Text(entry.matkul, modifier = Modifier.weight(2.2f), fontSize = 13.sp)
                    Text(entry.skor.toString(), modifier = Modifier.weight(0.8f), textAlign = TextAlign.Center, fontSize = 13.sp)
                    Text(
                        entry.nilai, 
                        modifier = Modifier.weight(0.8f),
                        textAlign = TextAlign.Center, 
                        fontWeight = FontWeight.Bold,
                        color = if (entry.nilai.startsWith("A")) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurface
                    )
                }
                if (index < grades.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    }
}

@Composable
fun IPSummaryTable(ipk: String, totalSks: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "IP Sementara",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    ipk,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            VerticalDivider(
                modifier = Modifier.height(48.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.2f)
            )
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    "Total SKS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
                Text(
                    totalSks,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataNilaiScreenPreview() {
    ProfilMahasiswaTheme {
        DataNilaiScreen()
    }
}
