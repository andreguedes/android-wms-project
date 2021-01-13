package br.com.deiviti.wms.ui.armazem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deiviti.wms.R
import kotlinx.android.synthetic.main.activity_armazem_list.*

class ArmazemListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_armazem_list)
    }

    override fun onResume() {
        super.onResume()

        rv_armazens.layoutManager = LinearLayoutManager(this)
        rv_armazens.adapter = ArmazemListAdapter()
    }

}
