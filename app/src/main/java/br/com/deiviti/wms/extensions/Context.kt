package br.com.deiviti.wms.extensions

import android.content.Context
import android.widget.Toast

fun Context.customToast(
    context: Context,
    message: String
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
