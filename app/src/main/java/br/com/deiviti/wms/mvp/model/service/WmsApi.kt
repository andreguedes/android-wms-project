package br.com.deiviti.wms.mvp.model.service

import br.com.deiviti.wms.mvp.model.shared.*
import retrofit2.Call
import retrofit2.http.*

interface WmsApi {

    @POST("/wms/v1/auth/login")
    fun postLogin(@Body userRequest: UserRequest): Call<UserResponse>

    @GET("/wms/v1/armazem")
    fun getArmazens(@Header("Authorization") token: String): Call<List<Armazem>>

    @GET("/wms/v1/armazem/{id_armazem}/tipoTarefa")
    fun getTiposTarefa(
        @Header("Authorization") tokenExtra: String,
        @Path("id_armazem") idArmazem: Int
    ): Call<List<TipoTarefaArmazem>>

    @GET("/wms/v1/armazem/{id_armazem}/armazenagem/tarefa/pendente")
    fun getArmazenagens(
        @Header("Authorization") tokenExtra: String = TOKEN,
        @Path("id_armazem") idArmazem: Int
    ): Call<List<Armazenagens>>

    companion object {
        private const val TOKEN = "hjklaljkhaldo9a87d6219he1h0872678wqhdloajhbldkjah786t2eughwl"
    }

}
