package br.com.deiviti.wms.mvp.view.ui.armazem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.shared.Armazem
import kotlinx.android.synthetic.main.item_layout_armazem.view.*

class ArmazemListAdapter(
    val clickListener: (Armazem) -> Unit
) : RecyclerView.Adapter<ArmazemListAdapter.ArmazemListViewHolder>() {

    private var armazemList: MutableList<Armazem> = mutableListOf()

    // SOLID
    inner class ArmazemListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun geraUmItemDaLista(armazem: Armazem) {
            with(itemView) {
                txt_armazem_id.text = armazem.code.toString()
                txt_armazem_nome.text = armazem.name
            }
            itemView.setOnClickListener {
                clickListener.invoke(armazem)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmazemListViewHolder {
        val layoutArmazemItem =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_armazem, parent, false)
        return ArmazemListViewHolder(layoutArmazemItem)
    }

    override fun onBindViewHolder(holder: ArmazemListViewHolder, position: Int) {
        val armazemItem = armazemList[position]
        holder.geraUmItemDaLista(armazemItem)
    }

    override fun getItemCount() = armazemList.size

    fun update(armazens: List<Armazem>) {
        armazemList.addAll(armazens)
        notifyDataSetChanged()
    }

}
