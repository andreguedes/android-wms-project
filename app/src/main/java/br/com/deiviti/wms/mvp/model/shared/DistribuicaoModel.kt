package br.com.deiviti.wms.mvp.model.shared

import com.google.gson.annotations.SerializedName

data class DistribuicaoModel(
    @SerializedName("ean") val ean: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("tamanho") val tamanho: Int,
    @SerializedName("quantidade") val quantidade: Int,
)