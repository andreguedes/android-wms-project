package br.com.deiviti.wms.mvp.model.shared

import com.google.gson.annotations.SerializedName

data class CodigoDeBarrasResponseModel(
    @SerializedName("volume") val volume: VolumeModel
)