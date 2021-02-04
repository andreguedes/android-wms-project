package br.com.deiviti.wms.mvp.view.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.deiviti.wms.R
import br.com.deiviti.wms.extensions.customToast
import br.com.deiviti.wms.mvp.presenter.login.LoginContract
import br.com.deiviti.wms.mvp.presenter.login.LoginPresenter
import br.com.deiviti.wms.mvp.view.ui.armazem.ArmazemListActivity
import br.com.deiviti.wms.mvp.view.utils.CustomProgressDialog
import br.com.deiviti.wms.preferences.CustomPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        presenter = LoginPresenter(this, this@LoginActivity)

        btn_login.setOnClickListener {
            presenter.postLogin(
                edt_username.text.toString(),
                edt_password.text.toString()
            )
        }

        val username = CustomPreferences.getStringPreferences(this, CustomPreferences.USERNAME)
        val password = CustomPreferences.getStringPreferences(this, CustomPreferences.PASSWORD)
        if (username != null && password != null) {
            presenter.postLogin(
                username,
                password
            )
        }
    }

    override fun showServerError() {
        customToast(this, "Erro Servidor")
    }

    override fun initializeArmazemListActivity(token: String) {
        CustomPreferences.putStringPreferences(
            this,
            CustomPreferences.USERNAME,
            edt_username.text.toString()
        )
        CustomPreferences.putStringPreferences(
            this,
            CustomPreferences.PASSWORD,
            edt_password.text.toString()
        )

        val intent = Intent(this@LoginActivity, ArmazemListActivity::class.java)
        intent.putExtra(ArmazemListActivity.EXTRA_TOKEN, token)
        startActivity(intent)
        finish()
    }

    override fun showErrorMessage(errorMessage: String) {
        CustomProgressDialog.showToast(this, errorMessage)
    }

}
