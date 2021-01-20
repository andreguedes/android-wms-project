package br.com.deiviti.wms.mvp.presenter.login

import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.UserRequest
import br.com.deiviti.wms.mvp.model.shared.UserResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginContract.View) : LoginContract.Presenter {

    override fun postLogin(userName: String, password: String) {
        val postLoginCallback = WmsRepository().postLogin(
            UserRequest(
                userName, password
            )
        )

        postLoginCallback.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    view.initializeArmazemListActivity(response.body()!!.token)
                } else {
                    val error = response.errorBody()!!.string()
                    val errorMessage = JSONObject(error).getString("message")
                    view.showErrorMessage(errorMessage)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.showServerError()
            }
        })
    }

}
