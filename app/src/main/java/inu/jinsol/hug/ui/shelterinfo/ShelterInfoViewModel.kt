package inu.jinsol.hug.ui.shelterinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShelterInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is introduction shelter Fragment"
    }
    val text: LiveData<String> = _text
}