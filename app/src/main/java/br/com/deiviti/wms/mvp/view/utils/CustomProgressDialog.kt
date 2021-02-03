package br.com.deiviti.wms.mvp.view.utils

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast

object CustomProgressDialog {

    fun getDialog(
        context: Context,
        title: String,
        message: String = "Carregando informações"
    ): ProgressDialog {
        val dialog = ProgressDialog(context)
        dialog.setTitle(title)
        dialog.setMessage(message)
        return dialog
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
