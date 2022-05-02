package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Solamente funciona el Fragment de Inventario\n\n" +
                "Para insertar un nuevo articulo dar clic en INSERTAR\n" +
                "Los Articulos guardados se mostraran en un ListView\n" +
                "Para Editar o Eliminar dar clic sobre alguno de los articulos del ListView\n\n" +
                "Es todo lo que hace xd\n\n#NOMEREPRUEBESBENIGNO"
    }
    val text: LiveData<String> = _text
}