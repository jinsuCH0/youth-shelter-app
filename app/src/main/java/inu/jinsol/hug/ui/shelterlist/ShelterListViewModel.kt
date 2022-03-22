package inu.jinsol.hug.ui.shelterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShelterListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is list of shelter Fragment"
    }
    val text: LiveData<String> = _text
}