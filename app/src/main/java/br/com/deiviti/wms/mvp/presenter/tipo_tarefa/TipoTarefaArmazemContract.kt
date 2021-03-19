package br.com.deiviti.wms.mvp.presenter.tipo_tarefa

import br.com.deiviti.wms.mvp.model.shared.Armazem
import br.com.deiviti.wms.mvp.model.shared.TipoTarefaArmazem

interface TipoTarefaArmazemContract {

    interface View {
        fun updateAdapter(tipoTarefaArmazemList: List<TipoTarefaArmazem>)
        fun showErrorMessage()
    }

    interface Presenter {
        fun getTipoTarefas(tokenExtra: String, armazem: Armazem)
    }

}