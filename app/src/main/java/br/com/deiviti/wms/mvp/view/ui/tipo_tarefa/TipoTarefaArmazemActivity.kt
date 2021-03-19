package br.com.deiviti.wms.mvp.view.ui.tipo_tarefa

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.shared.Armazem
import br.com.deiviti.wms.mvp.model.shared.TipoTarefaArmazem
import br.com.deiviti.wms.mvp.presenter.tipo_tarefa.TipoTarefaArmazemContract
import br.com.deiviti.wms.mvp.presenter.tipo_tarefa.TipoTarefaArmazemPresenter
import br.com.deiviti.wms.mvp.view.ui.codigo_barras.CodigoBarrasActivity
import br.com.deiviti.wms.mvp.view.ui.tarefa_armazenagem.TarefaArmazenagemActivity
import kotlinx.android.synthetic.main.activity_tipo_tarefa_armazem.*

class TipoTarefaArmazemActivity : AppCompatActivity(), TipoTarefaArmazemContract.View {

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
            handleSigla(it)
        }

        rv_tipos_tarefa_armazem.apply {
            this.layoutManager = GridLayoutManager(this@TipoTarefaArmazemActivity, 2)
            this.adapter = tipoTarefaArmazemAdapter
        }
    }

    private fun initData() {
        tokenExtra = intent.getStringExtra(EXTRA_TOKEN) ?: ""

        TipoTarefaArmazemPresenter(this).getTipoTarefas(tokenExtra, armazem)
    }

    private fun setTipoTarefaArmazemTitle(armazem: Armazem) {
        title = armazem.name + ": " + armazem.code
//        title = CustomPreferences.getStringPreferences(this, CustomPreferences.USERNAME)
    }

    override fun updateAdapter(tipoTarefaArmazemList: List<TipoTarefaArmazem>) {
        tipoTarefaArmazemAdapter!!.update(tipoTarefaArmazemList)
    }

    override fun showErrorMessage() {
        Toast.makeText(
                    this@TipoTarefaArmazemActivity,
                    "Erro ao carregar tipos de tarefas para o armazem ${armazem.name}",
                    Toast.LENGTH_SHORT
                ).show()
    }

    private fun handleSigla(sigla: String) {
        when(sigla) {
            TipoTarefaArmazemEnum.SEPARACAO.sigla -> {
                // TODO Implementar
            }
            TipoTarefaArmazemEnum.CONSULTA_CODIGO_BARRAS.sigla -> {
                val intent = Intent(this, CodigoBarrasActivity::class.java)
                startActivity(intent)
            }
            TipoTarefaArmazemEnum.ARMAZENAGEM.sigla -> {
                val intent = Intent(this, TarefaArmazenagemActivity::class.java)
                intent.putExtra(TarefaArmazenagemActivity.ARMAZEM_ID, armazem.code.toInt())
                startActivity(intent)
            }
            else -> {
                Toast.makeText(
                    this@TipoTarefaArmazemActivity,
                    "Tipo de tarefa ainda não implementado", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val EXTRA_TOKEN = "TOKEN"
        const val EXTRA_ARMAZEM = "ARMAZEM"
    }

}
