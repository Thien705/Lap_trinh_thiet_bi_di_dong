// File: DataModels.kt
package com.example.btvn1

// Định nghĩa cấu trúc cho một Sinh viên
data class Student(val id: Int, val name: String)

// Định nghĩa cấu trúc cho một Sách
data class Book(val id: Int, val title: String)

// Lớp này chứa TOÀN BỘ dữ liệu mà giao diện cần để hiển thị
data class LibraryUiState(
    val students: List<Student> = emptyList(),
    val books: List<Book> = emptyList(), // Danh sách sách tổng
    val selectedStudent: Student? = null,
    // Map để lưu sách mà mỗi sinh viên đã mượn (Key: studentId, Value: danh sách sách)
    val borrowedBooks: Map<Int, MutableList<Book>> = emptyMap()
)