package br.com.deiviti.wms.ui.tipo_tarefa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.deiviti.wms.R
import br.com.deiviti.wms.model.TipoTarefaArmazem
import kotlinx.android.synthetic.main.item_tipo_tarefa_armazem.view.*

class TipoTarefaArmazemAdapter : RecyclerView.Adapter<TipoTarefaArmazemAdapter.TipoTarefaArmazemViewHolder>() {

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
        notifyDataSetChanged()
    }

    inner class TipoTarefaArmazemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun geraUmItemDaLista(tipoTarefaArmazem: TipoTarefaArmazem) {
            with(itemView) {
                txt_tipo_tarefa.text = tipoTarefaArmazem.descricao
            }
        }

    }

}
