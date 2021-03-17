package br.com.deiviti.wms.mvp.view.ui.tipo_tarefa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.shared.TipoTarefaArmazem
import kotlinx.android.synthetic.main.item_tipo_tarefa_armazem.view.*

class TipoTarefaArmazemAdapter(
    val callback: (String) -> Unit
) : RecyclerView.Adapter<TipoTarefaArmazemAdapter.TipoTarefaArmazemViewHolder>() {

    private val tiposTarefaArmazemList = mutableListOf<TipoTarefaArmazem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoTarefaArmazemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tipo_tarefa_armazem, parent, false)
        return TipoTarefaArmazemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TipoTarefaArmazemViewHolder, position: Int) {
        holder.geraUmItemDaLista(tiposTarefaArmazemList[position])
    }

    override fun getItemCount() = tiposTarefaArmazemList.size

    fun update(tiposTarefaArmazem: List<TipoTarefaArmazem>) {
        tiposTarefaArmazemList.addAll(tiposTarefaArmazem)
        tiposTarefaArmazemList.addAll(getNewTipoTarefaArmazem())
        notifyDataSetChanged()
    }

    private fun getNewTipoTarefaArmazem() = listOf(
            TipoTarefaArmazem(100, "CCB", "Consulta Código de Barras"),
            TipoTarefaArmazem(101, "CFG", "Configurações")
        )

    inner class TipoTarefaArmazemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun geraUmItemDaLista(tipoTarefaArmazem: TipoTarefaArmazem) {
            with(itemView) {
                txt_tipo_tarefa.text = tipoTarefaArmazem.descricao
            }
            itemView.setOnClickListener {
                callback.invoke(tipoTarefaArmazem.sigla)
            }
        }

    }

}
