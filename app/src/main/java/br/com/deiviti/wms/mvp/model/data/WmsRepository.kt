package br.com.deiviti.wms.mvp.model.data

import br.com.deiviti.wms.mvp.model.shared.UserRequest
import br.com.deiviti.wms.mvp.model.service.RetrofitClient

class WmsRepository {

    fun postLogin(userRequest: UserRequest) = getRetrofitClient().postLogin(userRequest)

    fun getArmazens(token: String) = getRetrofitClient().getArmazens(token)

    fun getTiposTarefa(tokenExtra: String, idArmazem: Int) = getRetrofitClient().getTiposTarefa(tokenExtra, idArmazem)

    private fun getRetrofitClient() = RetrofitClient.getClient()

}
