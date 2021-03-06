package br.com.deiviti.wms.mvp.model.shared

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Armazem(
    @SerializedName("id") val code: Int,
    @SerializedName("nome") val name: String,
    @SerializedName("lerVolumeSeparacao") val flag: String
): Serializable
