package mx.tecnm.ladm_u3_practica1_danielsandovalalvarez_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException
import java.sql.SQLSyntaxErrorException

class  Inventario (este:Context){
    private val este = este
    var codigoBarras = ""
    var tipoEquipo = ""
    var caracteristicas = ""
    var fechaCompra = ""
    private var err = ""

    fun insertar():Boolean{
        val baseDatos = BaseDatos(este, "computo", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            val datos = ContentValues()

            datos.put("CODIGOBARRAS", codigoBarras)
            datos.put("TIPOEQUIPO", tipoEquipo)
            datos.put("CARACTERISTICAS", caracteristicas)
            datos.put("FECHACOMPRA",fechaCompra)

            val respuesta = tabla.insert("INVENTARIO",null,datos)
            if(respuesta == -1L){
                return false
            }
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return true
    }//Fin insertar()

    fun mostrarInvetario():ArrayList<Inventario>{
        val baseDatos = BaseDatos(este, "computo", null, 1)
        err = ""
        var arreglo = ArrayList<Inventario>()
        try {
            val tabla = baseDatos.readableDatabase
            val SQLSELECT = "SELECT * FROM INVENTARIO"

            var cursor = tabla.rawQuery(SQLSELECT,null)
            if(cursor.moveToFirst()){
                do {
                    val inventario = Inventario(este)
                    inventario.codigoBarras = cursor.getString(0)
                    inventario.tipoEquipo = cursor.getString(1)
                    inventario.caracteristicas = cursor.getString(2)
                    inventario.fechaCompra = cursor.getString(3)
                    arreglo.add(inventario)
                }while (cursor.moveToNext())
            }
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return arreglo
    }//mostrarInvetario()

    fun mostrarArticulo(codigoBarraX:String): Inventario{
        val baseDatos = BaseDatos(este, "computo", null, 1)
        err = ""
        val inventario = Inventario(este)
        try {
            val tabla = baseDatos.readableDatabase
            val SQLSELECT = "SELECT * FROM INVENTARIO WHERE CODIGOBARRAS=?"

            val cursor = tabla.rawQuery(SQLSELECT, arrayOf(codigoBarraX))
            if (cursor.moveToFirst()){
                inventario.codigoBarras = cursor.getString(0)
                inventario.tipoEquipo = cursor.getString(1)
                inventario.caracteristicas = cursor.getString(2)
                inventario.fechaCompra = cursor.getString(3)
            }
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return inventario
    }//mostrarArticulo()

    fun actualizar():Boolean{
        val baseDatos = BaseDatos(este, "computo", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("TIPOEQUIPO",tipoEquipo)
            datosActualizados.put("CARACTERISTICAS",caracteristicas)
            datosActualizados.put("FECHACOMPRA",fechaCompra)

            val respuesta = tabla.update("INVENTARIO",datosActualizados,
                                        "CODIGOBARRAS=?", arrayOf(codigoBarras))
            if (respuesta==0)return false
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return true
    }//actualizar

    fun eliminar():Boolean{
        val baseDatos = BaseDatos(este, "computo", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val resultado = tabla.delete("INVENTARIO","CODIGOBARRAS=?", arrayOf(codigoBarras))
            if (resultado==0)return false
        }catch (err:SQLiteException){
            this.err = err.message!!
        }finally {
            baseDatos.close()
        }
        return true
    }//eliminar

} //Fin Class Inventario

/*
val baseDatos = BaseDatos(este, "computo", null, 1)
err = ""
try {

}catch (err:SQLiteException){
    this.err = err.message!!
}finally {
    baseDatos.close()
}
*/