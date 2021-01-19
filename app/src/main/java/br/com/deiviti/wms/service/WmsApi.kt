package br.com.deiviti.wms.service

import br.com.deiviti.wms.model.Armazem
import br.com.deiviti.wms.model.UserRequest
import br.com.deiviti.wms.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface WmsApi {

    @POST("/wms/v1/auth/login")
    fun postLogin(@Body userRequest: UserRequest): Call<UserResponse>

    @GET("/armazem")
    fun getArmazens(@Header("Authorization") token: String): Call<List<Armazem>>

}
