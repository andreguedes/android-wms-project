package br.com.deiviti.wms.mvp.view.ui.tarefa_armazenagem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.shared.Armazenagens
import kotlinx.android.synthetic.main.item_tarefa_armazenagem.view.*

class ArmazenagemAdapter : RecyclerView.Adapter<ArmazenagemAdapter.ArmazeganemViewHolder>() {

    private var armazenagensList = mutableListOf<Armazenagens>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmazeganemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarefa_armazenagem, parent, false)
        return ArmazeganemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArmazeganemViewHolder, position: Int) {
        holder.geraArmazenagem(armazenagensList[position])
    }

    override fun getItemCount() = armazenagensList.size

    fun update(armazenagensList: List<Armazenagens>) {
        this.armazenagensList.addAll(armazenagensList)
        notifyDataSetChanged()
    }

    fun filter(code: String): Armazenagens {
        return armazenagensList.first {
            it.codigoBarrasEnderecoOrigem == code
        }
    }

    inner class ArmazeganemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun geraArmazenagem(armazenagens: Armazenagens) {
            with(itemView) {
                txt_tarefa_armazenagem_local.text = armazenagens.visualEnderecoOrigem
            }
        }

    }

}
