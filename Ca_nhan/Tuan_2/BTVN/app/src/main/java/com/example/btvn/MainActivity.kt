package com.example.btvn

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khai báo view theo ID trong XML
        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtTuoi = findViewById<EditText>(R.id.edtTuoi)
        val btnKiemTra = findViewById<Button>(R.id.btnKiemTra)
        val tvKetQua = findViewById<TextView>(R.id.tvKetQua)

        btnKiemTra.setOnClickListener {
            val name = edtHoTen.text.toString().trim()
            val ageText = edtTuoi.text.toString().trim()

            // Kiểm tra nhập liệu
            if (name.isEmpty() || ageText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ tên và tuổi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null || age < 0) {
                Toast.makeText(this, "Tuổi không hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Phân loại theo tuổi
            val category = when {
                age > 65 -> "Người già"
                age in 6..65 -> "Người lớn"
                age in 2..5 -> "Trẻ em"
                else -> "Em bé"
            }

            // Hiển thị kết quả
            tvKetQua.text = "Tên: $name\nTuổi: $age\nPhân loại: $category"
        }
    }
}
