package br.com.deiviti.wms.mvp.view.ui.tarefa_armazenagem

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.Armazenagens
import kotlinx.android.synthetic.main.activity_tarefa_armazenagem.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TarefaArmazenagemActivity : AppCompatActivity() {

    private lateinit var adapter: ArmazenagemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tarefa_armazenagem)
    }

    override fun onResume() {
        super.onResume()

        initView()
        initData()
    }

    private fun initView() {
        adapter = ArmazenagemAdapter()

        rv_tarefa_armazem.layoutManager = LinearLayoutManager(this)
        rv_tarefa_armazem.adapter = adapter

        btn_filter.setOnClickListener {
            adapter.filter("1234455566")
        }
    }

    private fun initData() {
        val armazemId = intent.getIntExtra(ARMAZEM_ID, 0)

        val armazenagensCallback = WmsRepository().getArmazenagens(armazemId)
        armazenagensCallback.enqueue(object : Callback<List<Armazenagens>> {
            override fun onResponse(
                call: Call<List<Armazenagens>>,
                response: Response<List<Armazenagens>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        updateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<Armazenagens>>, t: Throwable) {
                Toast.makeText(this@TarefaArmazenagemActivity, t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun updateAdapter(armazenagensList: List<Armazenagens>) {
        adapter.update(armazenagensList)
    }

    companion object {

        const val ARMAZEM_ID = "ARMAZEM_ID"

    }

}
