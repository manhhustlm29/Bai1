package com.example.bai1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioEven = findViewById<RadioButton>(R.id.radioEven)
        val radioOdd = findViewById<RadioButton>(R.id.radioOdd)
        val radioPerfectSquare = findViewById<RadioButton>(R.id.radioPerfectSquare)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listViewResults = findViewById<ListView>(R.id.listViewResults)
        val editTextMessage = findViewById<EditText>(R.id.editTextMessage)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty()) {
                editTextMessage.setText("Vui lòng nhập một số hợp lệ")
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n < 0) {
                editTextMessage.setText("Vui lòng nhập số nguyên không âm")
                return@setOnClickListener
            }

            val resultList = when {
                radioEven.isChecked -> (0..n).filter { it % 2 == 0 }
                radioOdd.isChecked -> (0..n).filter { it % 2 != 0 }
                radioPerfectSquare.isChecked -> (0..n).filter { k -> Math.sqrt(k.toDouble()).toInt().let { it * it == k } }
                else -> listOf()
            }

            if (resultList.isEmpty()) {
                editTextMessage.setText("Không có số thỏa mãn")
            } else {
                editTextMessage.text.clear()
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
                listViewResults.adapter = adapter
            }
        }
        editTextNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Kiểm tra nếu EditText trống
                if (s.isNullOrEmpty()) {
                    // Xóa nội dung ListView
                    listViewResults.adapter = null
                    editTextMessage.setText("Vui lòng nhập một số hợp lệ")
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })


    }
}