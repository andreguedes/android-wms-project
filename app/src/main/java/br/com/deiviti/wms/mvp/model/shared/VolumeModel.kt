package br.com.deiviti.wms.mvp.model.shared

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VolumeModel(
    @SerializedName("numeroSerie") val numeroSerie: String,
    @SerializedName("armazemCriacao") val armazemCriacao: Int,
    @SerializedName("dataCriacao") val dataCriacao: String,
    @SerializedName("armazem") val armazem: Int,
    @SerializedName("codigoEmbalagem") val codigoEmbalagem: String,
    @SerializedName("descricaoEmbalagem") val descricaoEmbalagem: String,
    @SerializedName("codigoDistribuicao") val codigoDistribuicao: String,
    @SerializedName("descricaoDistribuicao") val descricaoDistribuicao: String,
    @SerializedName("enderecoVisual") val enderecoVisual: String,
    @SerializedName("nomeArea") val nomeArea: String,
    @SerializedName("distribuicao") val distribuicao: List<DistribuicaoModel>

) : Serializable