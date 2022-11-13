package com.example.kotlintestproject

import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.kotlintestproject.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var etData: EditText
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        val etId = findViewById<EditText>(R.id.etId)
        etData = findViewById<EditText>(R.id.etData)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnLoad = findViewById<Button>(R.id.btnLoad)

        btnSave.setOnClickListener {
            viewModel.saveData(
                etId.text.toString(),
                etData.text.toString()
            )
        }
        btnLoad.setOnClickListener { viewModel.loadData(etId.text.toString()) }
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        viewModel.data.observe(this) {
            if (it != null) {
                etData.setText(it.data)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.data.removeObservers(this)
    }
}