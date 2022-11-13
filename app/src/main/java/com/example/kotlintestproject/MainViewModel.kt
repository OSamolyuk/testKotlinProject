package com.example.kotlintestproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testkotlinproject.room.TestDao
import com.example.testkotlinproject.room.TestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var dao: TestDao

    protected val _data: MutableLiveData<TestModel?> = MutableLiveData()
    val data: LiveData<TestModel?> = _data

    fun saveData(id: String?, data: String) {
        if (id.isNullOrEmpty()) {
            _data.value = null
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertTestData(
                TestModel(
                    Integer.valueOf(id),
                    data
                )
            )
        }
    }

    fun loadData(id: String?) {
        if (id.isNullOrEmpty()) {
            _data.value = null
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            val testData = dao.getSingleTestData(Integer.valueOf(id))
            viewModelScope.launch(Dispatchers.Main) {
                _data.value = testData
            }
        }
    }
}