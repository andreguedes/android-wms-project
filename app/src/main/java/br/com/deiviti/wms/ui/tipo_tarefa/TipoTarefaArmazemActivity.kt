package br.com.deiviti.wms.ui.tipo_tarefa

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.model.Armazem
import kotlinx.android.synthetic.main.activity_tipo_tarefa_armazem.*

class TipoTarefaArmazemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tipo_tarefa_armazem)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
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
        val armazem = intent.getSerializableExtra(EXTRA_ARMAZEM) as Armazem
        setTipoTarefaArmazemTitle(armazem)

        rv_tipos_tarefa_armazem.apply {
            this.layoutManager = GridLayoutManager(this@TipoTarefaArmazemActivity, 2)
            adapter = TipoTarefaArmazemAdapter()
        }
    }

    private fun initData() {
        val tokenExtra = intent.getStringExtra(EXTRA_TOKEN)
        // TODO Chamar a API passando o Token com o modelo de initData()
    }

    private fun setTipoTarefaArmazemTitle(armazem: Armazem) {
        title = armazem.name + ": " + armazem.code
    }

    companion object {
        const val EXTRA_TOKEN = "TOKEN"
        const val EXTRA_ARMAZEM = "ARMAZEM"
    }

}
