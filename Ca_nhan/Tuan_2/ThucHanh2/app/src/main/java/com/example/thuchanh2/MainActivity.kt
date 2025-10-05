package com.example.thuchanh2

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)
        val btnTao = findViewById<Button>(R.id.btnTao)
        val tvThongBao = findViewById<TextView>(R.id.tvThongBao)
        val layoutList = findViewById<LinearLayout>(R.id.layoutList)

        btnTao.setOnClickListener {
            val nhap = edtNumber.text.toString().trim()
            layoutList.removeAllViews()
            tvThongBao.visibility = View.GONE

            // Kiểm tra dữ liệu có phải là số hay không
            if (nhap.matches(Regex("\\d+"))) {
                val soLuong = nhap.toInt()

                // Nếu là số -> tạo danh sách
                for (i in 1..soLuong) {
                    val btn = Button(this)
                    btn.text = i.toString()
                    btn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                    btn.setTextColor(ContextCompat.getColor(this, android.R.color.white))
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        130
                    )
                    params.setMargins(0, 10, 0, 0)
                    btn.layoutParams = params
                    btn.gravity = Gravity.CENTER
                    layoutList.addView(btn)
                }
            } else {
                // Nếu không phải kiểu số -> hiển thị thông báo lỗi
                tvThongBao.visibility = View.VISIBLE
                tvThongBao.text = "Dữ liệu bạn nhập không hợp lệ"
            }
        }
    }
}
