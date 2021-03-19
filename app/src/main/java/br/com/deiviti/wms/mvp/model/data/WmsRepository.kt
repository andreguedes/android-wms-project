package br.com.deiviti.wms.mvp.model.data

import br.com.deiviti.wms.mvp.model.shared.UserRequest
import br.com.deiviti.wms.mvp.model.service.RetrofitClient
import br.com.deiviti.wms.mvp.model.shared.UserRequestArmazem

class WmsRepository {

    fun postLogin(userRequest: UserRequest) = getRetrofitClient().postLogin(userRequest)

    fun getArmazens(token: String) = getRetrofitClient().getArmazens(token)

    fun getTiposTarefa(tokenExtra: String, idArmazem: Int) =
        getRetrofitClient().getTiposTarefa(tokenExtra, idArmazem)

    fun getArmazenagens(idArmazem: Int) = getRetrofitClient().getArmazenagens(idArmazem = idArmazem)

    fun getCodigoDeBarras(
        idArmazem: Int,
        codigoDeBarras: String,
        userRequestArmazem: UserRequestArmazem
    ) =
        getRetrofitClient().getCodigodeBarras(
            idArmazem = idArmazem,
            codigoBarras = codigoDeBarras,
            userRequestArmazem = userRequestArmazem
        )

    private fun getRetrofitClient() = RetrofitClient.getClient()

}
