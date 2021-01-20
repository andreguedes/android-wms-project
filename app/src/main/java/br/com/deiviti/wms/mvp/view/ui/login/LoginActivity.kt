package br.com.deiviti.wms.mvp.view.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.presenter.login.LoginContract
import br.com.deiviti.wms.mvp.presenter.login.LoginPresenter
import br.com.deiviti.wms.mvp.view.ui.armazem.ArmazemListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        presenter = LoginPresenter(this@LoginActivity)

        btn_login.setOnClickListener {
            presenter.postLogin(
                edt_username.text.toString(),
                edt_password.text.toString()
            )
        }
    }

    override fun showServerError() {
        Toast.makeText(this@LoginActivity, "Erro Servidor", Toast.LENGTH_SHORT).show()
    }

    override fun initializeArmazemListActivity(token: String) {
        val intent = Intent(this@LoginActivity, ArmazemListActivity::class.java)
        intent.putExtra(ArmazemListActivity.EXTRA_TOKEN, token)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT).show()
    }

}
