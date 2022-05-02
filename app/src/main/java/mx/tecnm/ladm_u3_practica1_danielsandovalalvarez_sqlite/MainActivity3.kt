package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.databinding.ActivityMain2Binding
import mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain3Binding
    var codigo = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        codigo = this.intent.extras!!.getString("codigo")!!
        val inventario = Inventario(this).mostrarArticulo(codigo)

        binding.txtCodigo.setText(inventario.codigoBarras)
        binding.editTipo.setText(inventario.tipoEquipo)
        binding.editCarac.setText(inventario.caracteristicas)
        binding.editDate.setText(inventario.fechaCompra)

        binding.btnActualizar.setOnClickListener {
            inventario.tipoEquipo = binding.editTipo.text.toString()
            inventario.caracteristicas = binding.editCarac.text.toString()
            inventario.fechaCompra = binding.editDate.text.toString()

            val resultado = inventario.actualizar()
            if (resultado) {
                Toast.makeText(this, "SE HA ACTUALIZADO EL ARTICULO", Toast.LENGTH_LONG).show()
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("ERROR")
                    .setMessage("NO SE PUDO ACTUALIZAR")
                    .show()
            }
        }//btnActualizar

        binding.btnEliminar.setOnClickListener {
            inventario.eliminar()
            finish()
        }

        binding.btnSalir.setOnClickListener { finish() }
    }
}