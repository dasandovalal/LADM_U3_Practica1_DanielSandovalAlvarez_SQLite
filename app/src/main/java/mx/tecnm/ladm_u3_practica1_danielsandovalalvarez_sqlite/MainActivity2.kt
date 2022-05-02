package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardar.setOnClickListener {
            if(binding.editCodigo.text.isNotEmpty()) {
                var inventario = Inventario(this)

                inventario.codigoBarras = binding.editCodigo.text.toString()
                inventario.tipoEquipo = binding.editTipo.text.toString()
                inventario.caracteristicas = binding.editCarac.text.toString()
                inventario.fechaCompra = binding.editDate.text.toString()

                val resultado = inventario.insertar()
                if (resultado) {
                    Toast.makeText(this, "SE HA REGISTRADO EL ARTICULO", Toast.LENGTH_LONG).show()
                    binding.editCodigo.text.clear()
                    binding.editTipo.text.clear()
                    binding.editCarac.text.clear()
                    binding.editDate.text.clear()
                } else {
                    AlertDialog.Builder(this)
                        .setTitle("ERROR")
                        .setMessage("NO SE PUDO INSERTAR")
                        .show()
                }
            }
        }//btnGuardar()

        binding.btnSalir.setOnClickListener {
            finish()
        }
    }

}