package br.com.deiviti.wms.mvp.view.ui.codigo_barras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.shared.DistribuicaoModel
import kotlinx.android.synthetic.main.item_distribuicao_codigo_barras.view.*

class CodigoBarrasAdapter : RecyclerView.Adapter<CodigoBarrasAdapter.CodigoBarrasViewHolder>() {

    private var distribuicaoList = mutableListOf<DistribuicaoModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodigoBarrasViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_distribuicao_codigo_barras, parent, false)
        return CodigoBarrasViewHolder(view)
    }

    override fun getItemCount() = distribuicaoList.size

    override fun onBindViewHolder(holder: CodigoBarrasViewHolder, position: Int) {
        holder.onBind(distribuicaoList[position])
    }

    fun update(distriList: List<DistribuicaoModel>) {
        this.distribuicaoList.clear()
        this.distribuicaoList.addAll(distriList)

        notifyDataSetChanged()
    }

    inner class CodigoBarrasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(distribuicaoModel: DistribuicaoModel) {
            with(itemView) {
                distribuicaoModel.let {
                    txt_ean.text = it.ean
                    txt_sku.text = it.sku
                    txt_quantidade.text = it.quantidade.toString()
                    txt_tamanho.text = it.tamanho.toString()
                }
            }
        }

    }

}