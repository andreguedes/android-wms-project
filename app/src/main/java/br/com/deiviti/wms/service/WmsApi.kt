package br.com.deiviti.wms.service

import br.com.deiviti.wms.model.Armazem
import br.com.deiviti.wms.model.TipoTarefaArmazem
import br.com.deiviti.wms.model.UserRequest
import br.com.deiviti.wms.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface WmsApi {

    @POST("/wms/v1/auth/login")
    fun postLogin(@Body userRequest: UserRequest): Call<UserResponse>

    @GET("/armazem")
    fun getArmazens(@Header("Authorization") token: String): Call<List<Armazem>>

    @GET("/wms/v1/armazem/{id_armazem}/tipoTarefa")
    fun getTiposTarefa(
        @Header("Authorization") tokenExtra: String,
        @Path("id_armazem") idArmazem: Int
    ): Call<List<TipoTarefaArmazem>>

}
