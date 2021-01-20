package br.com.deiviti.wms.ui.armazem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.data.WmsRepository
import br.com.deiviti.wms.model.Armazem
import br.com.deiviti.wms.ui.tipo_tarefa.TipoTarefaArmazemActivity
import kotlinx.android.synthetic.main.activity_armazem_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmazemListActivity : AppCompatActivity() {

    private lateinit var token: String
    private var adapter: ArmazemListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_armazem_list)
    }

    override fun onResume() {
        super.onResume()

        initView()
        initData()
    }

    private fun initView() {
        adapter = ArmazemListAdapter {
            enviarArmazemParaTipoTarefa(it)
        }

        rv_armazens.layoutManager = LinearLayoutManager(this)
        rv_armazens.adapter = adapter
    }

    private fun initData() {
        token = intent.getStringExtra(EXTRA_TOKEN).toString()
        val getArmazensCallback = WmsRepository().getArmazens(
            token
        )

        getArmazensCallback.enqueue(object : Callback<List<Armazem>> {
            override fun onResponse(call: Call<List<Armazem>>, response: Response<List<Armazem>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Armazem>>, t: Throwable) {
                Toast.makeText(this@ArmazemListActivity, "Erro Servidor", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateAdapter(armazens: List<Armazem>) {
        adapter!!.update(armazens)
    }

    private fun enviarArmazemParaTipoTarefa(armazem: Armazem) {
        val intent = Intent(this, TipoTarefaArmazemActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable(TipoTarefaArmazemActivity.EXTRA_ARMAZEM, armazem)
        bundle.putString(TipoTarefaArmazemActivity.EXTRA_TOKEN, token)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_TOKEN = "TOKEN"
    }

}
