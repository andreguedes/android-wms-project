package br.com.deiviti.wms.preferences

import android.app.Activity
import android.content.Context

object CustomPreferences {

    const val USERNAME = "USERNAME"
    const val PASSWORD = "PASSWORD"

    private fun getPreferences(activity: Activity) = activity.getPreferences(Context.MODE_PRIVATE)

    private fun editPreferences(activity: Activity, key: String, string: String) {
        with(getPreferences(activity).edit()) {
            putString(key, string)
            apply()
        }
    }

    private fun deletePreferences(activity: Activity, key: String) {
        with(getPreferences(activity).edit()) {
            remove(key)
            apply()
        }
    }

    fun putStringPreferences(activity: Activity, key: String, string: String) {
        editPreferences(activity, key, string)
    }

    fun getStringPreferences(activity: Activity, key: String) : String? {
        return getPreferences(activity).getString(key, null)
    }

    fun deleteStringPreferences(activity: Activity, key: String) {
        deletePreferences(activity, key)
    }

}
