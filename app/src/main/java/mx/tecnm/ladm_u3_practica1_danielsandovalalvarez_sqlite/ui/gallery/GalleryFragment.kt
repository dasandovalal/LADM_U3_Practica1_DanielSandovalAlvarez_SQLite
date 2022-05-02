package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.ui.gallery

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.Inventario
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.MainActivity2
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.MainActivity3
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var listaArticulos = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mostrarDatosEnListView()

        binding.btnInsertar.setOnClickListener {
            var otraVentana = Intent(requireContext(), MainActivity2::class.java)
            startActivity(otraVentana)
        }//btnInsertar

        binding.lista.setOnItemClickListener { adapterView, view, i, l ->
            val codigoBarra = listaArticulos.get(i)
            val inventario = Inventario(requireContext()).mostrarArticulo(codigoBarra)

            var otraVentana = Intent(requireContext(), MainActivity3::class.java)
            otraVentana.putExtra("codigo",inventario.codigoBarras)
            startActivity(otraVentana)

        }//listaClickListener

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun mostrarDatosEnListView() {
        var listaInventario = Inventario(requireContext()).mostrarInvetario()
        var codigoInventario = ArrayList<String>()

        listaArticulos.clear()
        (0..listaInventario.size - 1).forEach {
            val al = listaInventario.get(it)
            codigoInventario.add(al.codigoBarras)
            listaArticulos.add(al.codigoBarras)
        }

        binding.lista.adapter =
            ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1, codigoInventario)
    }//mostrarDatosEnListView

    override fun onResume() {
        super.onResume()
        mostrarDatosEnListView()
    }
}