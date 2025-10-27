// File: MainActivity.kt
package com.example.btvn1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
// DÒNG NÀY RẤT QUAN TRỌNG, HÃY ĐẢM BẢO NÓ CÓ TRONG FILE CỦA BẠN
import com.example.btvn1.ui.theme.BTVN1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Sử dụng Theme đã được import
            BTVN1Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LibraryApp()
                }
            }
        }
    }
}

// ... (CÁC HÀM CÒN LẠI GIỮ NGUYÊN NHƯ ĐÃ CUNG CẤP TRƯỚC ĐÓ) ...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryApp(libraryViewModel: LibraryViewModel = viewModel()) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hệ thống Quản lý Thư viện") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = { AppBottomNavigation(navController) }
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            viewModel = libraryViewModel,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

object Routes {
    const val MANAGE = "manage"
    const val BOOKS = "books"
    const val STUDENTS = "students"
}

@Composable
fun AppNavHost(navController: NavHostController, viewModel: LibraryViewModel, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Routes.MANAGE,
        modifier = modifier
    ) {
        composable(Routes.MANAGE) { ManageScreen(viewModel = viewModel) }
        composable(Routes.BOOKS) { BookListScreen(viewModel = viewModel) }
        composable(Routes.STUDENTS) { StudentListScreen(viewModel = viewModel) }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
            label = { Text("Quản lý") },
            selected = currentRoute == Routes.MANAGE,
            onClick = { navController.navigate(Routes.MANAGE) { launchSingleTop = true } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "DS Sách") },
            label = { Text("DS Sách") },
            selected = currentRoute == Routes.BOOKS,
            onClick = { navController.navigate(Routes.BOOKS) { launchSingleTop = true } }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
            label = { Text("Sinh viên") },
            selected = currentRoute == Routes.STUDENTS,
            onClick = { navController.navigate(Routes.STUDENTS) { launchSingleTop = true } }
        )
    }
}

@Composable
fun ManageScreen(viewModel: LibraryViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedStudent = uiState.selectedStudent
    val borrowedBooks = selectedStudent?.let { uiState.borrowedBooks[it.id] } ?: emptyList()

    if (selectedStudent == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Vui lòng chọn một sinh viên từ tab Sinh viên")
        }
        return
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sinh viên", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = selectedStudent.name,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {}) { Text("Thay đổi") }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Danh sách sách đã mượn", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier.weight(1f)) {
            if (borrowedBooks.isEmpty()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Sinh viên chưa mượn sách nào.\nNhấn nút bên dưới để thêm sách mới.",
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                    }
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(borrowedBooks) { book ->
                        BookItem(book = book, isChecked = true) {
                            viewModel.returnBook(selectedStudent.id, book)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.addNewBookToStudent(selectedStudent.id) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Thêm Sách Mới")
        }
    }
}

@Composable
fun BookListScreen(viewModel: LibraryViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val allBooks = uiState.borrowedBooks.values.flatten().distinctBy { it.id }

    if (allBooks.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Chưa có sách nào được mượn trong hệ thống.")
        }
        return
    }
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(allBooks) { book ->
            Card(elevation = CardDefaults.cardElevation(2.dp)) {
                Text(text = book.title, modifier = Modifier.fillMaxWidth().padding(16.dp))
            }
        }
    }
}

@Composable
fun StudentListScreen(viewModel: LibraryViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(uiState.students) { student ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.selectStudent(student) },
                colors = if (uiState.selectedStudent?.id == student.id) {
                    CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                } else {
                    CardDefaults.cardColors()
                }
            ) {
                Text(text = student.name, modifier = Modifier.padding(16.dp), fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun BookItem(book: Book, isChecked: Boolean, onCheckChange: (Boolean) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(2.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = isChecked, onCheckedChange = onCheckChange)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = book.title, fontSize = 16.sp)
        }
    }
}