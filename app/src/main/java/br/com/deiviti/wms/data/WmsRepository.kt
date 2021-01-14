package br.com.deiviti.wms.data

import br.com.deiviti.wms.model.UserRequest
import br.com.deiviti.wms.model.UserResponse
import br.com.deiviti.wms.service.RetrofitClient
import retrofit2.Call

class WmsRepository {

    fun postLogin(userRequest: UserRequest): Call<UserResponse> {
        return RetrofitClient.getClient().postLogin(userRequest)
    }

}
