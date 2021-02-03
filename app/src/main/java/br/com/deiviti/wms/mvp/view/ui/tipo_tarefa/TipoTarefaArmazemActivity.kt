package br.com.deiviti.wms.mvp.view.ui.tipo_tarefa

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.Armazem
import br.com.deiviti.wms.mvp.model.shared.TipoTarefaArmazem
import br.com.deiviti.wms.mvp.view.ui.tarefa_armazenagem.TarefaArmazenagemActivity
import kotlinx.android.synthetic.main.activity_tipo_tarefa_armazem.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipoTarefaArmazemActivity : AppCompatActivity() {

    private lateinit var armazem: Armazem
    private var tokenExtra: String = ""

    private var tipoTarefaArmazemAdapter: TipoTarefaArmazemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tipo_tarefa_armazem)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setLogo(android.R.drawable.ic_dialog_alert)
        }
    }

    override fun onResume() {
        super.onResume()

        initView()
        initData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        armazem = intent.getSerializableExtra(EXTRA_ARMAZEM) as Armazem
        setTipoTarefaArmazemTitle(armazem)

        tipoTarefaArmazemAdapter = TipoTarefaArmazemAdapter {
            // TODO Implementar
            if (it == 3) {
                val intent = Intent(this, TarefaArmazenagemActivity::class.java)
                intent.putExtra(TarefaArmazenagemActivity.ARMAZEM_ID, armazem.code.toInt())
                startActivity(intent)
            } else {
                Toast.makeText(
                    this@TipoTarefaArmazemActivity,
                    "Tipo de tarefa ainda não implementado", Toast.LENGTH_SHORT
                ).show()
            }
        }

        rv_tipos_tarefa_armazem.apply {
            this.layoutManager = GridLayoutManager(this@TipoTarefaArmazemActivity, 2)
            this.adapter = tipoTarefaArmazemAdapter
        }
    }

    private fun initData() {
        tokenExtra = intent.getStringExtra(EXTRA_TOKEN) ?: ""

        val tiposTarefaCallback = WmsRepository().getTiposTarefa(tokenExtra, armazem.code.toInt())
        tiposTarefaCallback.enqueue(object : Callback<List<TipoTarefaArmazem>> {
            override fun onResponse(
                call: Call<List<TipoTarefaArmazem>>,
                response: Response<List<TipoTarefaArmazem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<TipoTarefaArmazem>>, t: Throwable) {
                Toast.makeText(
                    this@TipoTarefaArmazemActivity,
                    "Erro ao carregar tipos de tarefas para o armazem ${armazem.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setTipoTarefaArmazemTitle(armazem: Armazem) {
        title = armazem.name + ": " + armazem.code
    }

    private fun updateAdapter(tiposTarefaArmazem: List<TipoTarefaArmazem>) {
        tipoTarefaArmazemAdapter!!.update(tiposTarefaArmazem)
    }

    companion object {
        const val EXTRA_TOKEN = "TOKEN"
        const val EXTRA_ARMAZEM = "ARMAZEM"
    }

}
