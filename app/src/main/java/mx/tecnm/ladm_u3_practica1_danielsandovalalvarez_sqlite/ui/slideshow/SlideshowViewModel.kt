package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Proximamente xd"
    }
    val text: LiveData<String> = _text
}