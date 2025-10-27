// File: LibraryViewModel.kt
package com.example.btvn1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    // --- DỮ LIỆU GIẢ LẬP ---
    private val initialStudents = listOf(
        Student(1, "Nguyễn Văn A"),
        Student(2, "Nguyễn Thị B"),
        Student(3, "Nguyễn Văn C")
    )

    private val initialBooks = listOf(
        Book(101, "Sách 01"),
        Book(102, "Sách 02")
    )

    private var newBookCounter = 0

    // --- TRẠNG THÁI (STATE) ---
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    init {
        // Khởi tạo dữ liệu ban đầu
        _uiState.value = LibraryUiState(
            students = initialStudents,
            books = initialBooks,
            selectedStudent = initialStudents.first(),
            borrowedBooks = mutableMapOf(
                initialStudents[0].id to mutableListOf(initialBooks[0], initialBooks[1]),
                initialStudents[1].id to mutableListOf(initialBooks[0])
            )
        )
    }

    // --- HÀNH ĐỘNG (ACTIONS) ---
    fun selectStudent(student: Student) {
        _uiState.update { it.copy(selectedStudent = student) }
    }

    fun addNewBookToStudent(studentId: Int) {
        newBookCounter++
        val newBook = Book(id = 1000 + newBookCounter, title = "Sách Mới $newBookCounter")

        _uiState.update { currentState ->
            val updatedBorrowedBooks = currentState.borrowedBooks.toMutableMap()
            val currentBooks = updatedBorrowedBooks.getOrPut(studentId) { mutableListOf() }
            currentBooks.add(newBook)
            currentState.copy(borrowedBooks = updatedBorrowedBooks)
        }
    }

    fun returnBook(studentId: Int, bookToReturn: Book) {
        _uiState.update { currentState ->
            val updatedBorrowedBooks = currentState.borrowedBooks.toMutableMap()
            updatedBorrowedBooks[studentId]?.remove(bookToReturn)
            currentState.copy(borrowedBooks = updatedBorrowedBooks)
        }
    }
}