package br.com.deiviti.wms.mvp.presenter.login

import android.app.ProgressDialog
import android.content.Context
import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.UserRequest
import br.com.deiviti.wms.mvp.model.shared.UserResponse
import br.com.deiviti.wms.mvp.view.utils.CustomProgressDialog
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val context: Context, val view: LoginContract.View) : LoginContract.Presenter {

    val dialog = CustomProgressDialog.getDialog(context, "Realizando Login")

    override fun postLogin(userName: String, password: String) {
        dialog.show()

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
                dialog.hide()

                if (response.isSuccessful) {
                    view.initializeArmazemListActivity(response.body()!!.token)
                } else {
                    val error = response.errorBody()!!.string()
                    val errorMessage = JSONObject(error).getString("message")
                    view.showErrorMessage(errorMessage)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dialog.hide()

                view.showServerError()
            }
        })
    }

}
