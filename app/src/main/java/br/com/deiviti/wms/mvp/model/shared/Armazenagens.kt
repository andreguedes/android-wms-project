package br.com.deiviti.wms.mvp.model.shared

import com.google.gson.annotations.SerializedName

data class Armazenagens(
    @SerializedName("idAreaOrigem") val idAreaOrigem: Int,
    @SerializedName("idEnderecoOrigem") val idEnderecoOrigem: Int,
    @SerializedName("codigoBarrasEnderecoOrigem") val codigoBarrasEnderecoOrigem: String,
    @SerializedName("enstanteEnderecoOrigem") val enstanteEnderecoOrigem: String,
    @SerializedName("visualEnderecoOrigem") val visualEnderecoOrigem: String,
    @SerializedName("idAreaDestino") val idAreaDestino: Int,
    @SerializedName("idEnderecoDestino") val idEnderecoDestino: Int,
    @SerializedName("codigoBarrasEnderecoDestino") val codigoBarrasEnderecoDestino: String,
    @SerializedName("enstanteEnderecoDestino") val enstanteEnderecoDestino: String,
    @SerializedName("visualEnderecoDestino") val visualEnderecoDestino: String,
    @SerializedName("quantidade") val quantidade: Int
)
