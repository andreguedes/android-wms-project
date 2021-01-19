package br.com.deiviti.wms.model

import com.google.gson.annotations.SerializedName

data class Armazem(
    @SerializedName("id") val code: String,
    @SerializedName("nome") val name: String,
    @SerializedName("lerVolumeSeparacao") val flag: String
)
