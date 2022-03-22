package inu.jinsol.hug.ui.sheltersearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShelterSearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is nearest shelter finding Fragment"
    }
    val text: LiveData<String> = _text
}