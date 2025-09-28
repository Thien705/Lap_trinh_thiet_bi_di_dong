package com.example.tuan1

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tuan1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedImageUri: Uri? = null

    // Đăng ký launcher để chọn ảnh
    private val pickImage =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                selectedImageUri = uri
                binding.imgAvatar.setImageURI(uri) // hiển thị ảnh vừa chọn
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sự kiện khi bấm nút
        binding.btnShow.setOnClickListener {
            val name = binding.edtName.text.toString()
            if (name.isNotEmpty()) {
                binding.tvName.text = name
                // Nếu chưa chọn ảnh thì để avatar mặc định
                if (selectedImageUri == null) {
                    binding.imgAvatar.setImageResource(R.drawable.avatar)
                }
            } else {
                binding.tvName.text = "Vui lòng nhập tên"
            }
        }

        // Sự kiện khi bấm vào ảnh để chọn ảnh mới
        binding.imgAvatar.setOnClickListener {
            pickImage.launch("image/*")
        }
    }
}