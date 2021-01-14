package br.com.deiviti.wms.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.deiviti.wms.R
import br.com.deiviti.wms.data.WmsRepository
import br.com.deiviti.wms.model.UserRequest
import br.com.deiviti.wms.model.UserResponse
import br.com.deiviti.wms.ui.armazem.ArmazemListActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        btn_login.setOnClickListener {
            val postLoginCallback = WmsRepository().postLogin(
                UserRequest(
                    edt_username.text.toString(),
                    edt_password.text.toString()
                )
            )

            postLoginCallback.enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        val intent = Intent(this@LoginActivity, ArmazemListActivity::class.java)
                        startActivity(intent)
                    } else {
                        val error = response.errorBody()!!.string()
                        val errorMessaage = JSONObject(error).getString("message")
                        Toast.makeText(this@LoginActivity, errorMessaage, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Erro Servidor", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}
