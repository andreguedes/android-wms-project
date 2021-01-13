package br.com.deiviti.wms.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.deiviti.wms.R
import br.com.deiviti.wms.ui.armazem.ArmazemListActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        btn_login.setOnClickListener {
            val intent = Intent(this, ArmazemListActivity::class.java)
            startActivity(intent)
        }
    }

}
