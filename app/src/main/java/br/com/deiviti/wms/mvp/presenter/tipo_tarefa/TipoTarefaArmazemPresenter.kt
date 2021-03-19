package br.com.deiviti.wms.mvp.presenter.tipo_tarefa

import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.Armazem
import br.com.deiviti.wms.mvp.model.shared.TipoTarefaArmazem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TipoTarefaArmazemPresenter(val view: TipoTarefaArmazemContract.View) : TipoTarefaArmazemContract.Presenter {

    override fun getTipoTarefas(tokenExtra: String, armazem: Armazem) {
        val tiposTarefaCallback = WmsRepository().getTiposTarefa(tokenExtra, armazem.code)
        tiposTarefaCallback.enqueue(object : Callback<List<TipoTarefaArmazem>> {
            override fun onResponse(
                call: Call<List<TipoTarefaArmazem>>,
                response: Response<List<TipoTarefaArmazem>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        view.updateAdapter(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<TipoTarefaArmazem>>, t: Throwable) {
                view.showErrorMessage()
            }
        })
    }

}