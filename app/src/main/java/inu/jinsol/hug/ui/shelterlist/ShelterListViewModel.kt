package inu.jinsol.hug.ui.shelterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShelterListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is list of shelter Fragment"
    }
    val text: LiveData<String> = _text

    val cityList = listOf<String>("서울시", "인천시", "경기도")
}