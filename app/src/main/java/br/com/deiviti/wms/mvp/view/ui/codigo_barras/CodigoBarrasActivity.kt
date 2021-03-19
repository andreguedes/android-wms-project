package br.com.deiviti.wms.mvp.view.ui.codigo_barras

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.deiviti.wms.R
import br.com.deiviti.wms.mvp.model.data.WmsRepository
import br.com.deiviti.wms.mvp.model.shared.CodigoDeBarrasResponseModel
import br.com.deiviti.wms.mvp.model.shared.UserRequestArmazem
import kotlinx.android.synthetic.main.activity_codigo_barras.*
import kotlinx.android.synthetic.main.include_codigo_barras.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodigoBarrasActivity : AppCompatActivity() {

    private var codigoBarrasAdapter: CodigoBarrasAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_codigo_barras)
    }

    override fun onResume() {
        super.onResume()

        initView()
    }

    private fun initView() {
        codigoBarrasAdapter = CodigoBarrasAdapter()

        rv_distribuicoes.apply {
            layoutManager = LinearLayoutManager(this@CodigoBarrasActivity)
            this.adapter = codigoBarrasAdapter
        }

        // TODO Chamar o leitor de QRCode para preencher o campo de texto
        // TODO Depois que tiver o texto preenchido, chamar o initData, passando o QRCode

        initData("12345678")
    }

    private fun initData(numeroDeSerie: String) {
        setVisibilityToComponents(View.INVISIBLE)

        val userRequestArmazem = UserRequestArmazem("100", numeroDeSerie)

        val codigoDeBarrasCallback = WmsRepository().getCodigoDeBarras(0, numeroDeSerie, userRequestArmazem)
        codigoDeBarrasCallback.enqueue(object : Callback<CodigoDeBarrasResponseModel>{
            override fun onResponse(
                call: Call<CodigoDeBarrasResponseModel>,
                response: Response<CodigoDeBarrasResponseModel>
            ) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        fillFieldsForBarCodeInfo(it)
                    }

                } else {
                    setVisibilityToComponents(View.INVISIBLE)

                    Toast.makeText(this@CodigoBarrasActivity, response.errorBody().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<CodigoDeBarrasResponseModel>, t: Throwable) {
                setVisibilityToComponents(View.INVISIBLE)

                Toast.makeText(this@CodigoBarrasActivity, t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun fillFieldsForBarCodeInfo(codigoBarrasModel: CodigoDeBarrasResponseModel) {
        setVisibilityToComponents(View.VISIBLE)

        codigoBarrasModel.volume.let {
            txt_numerodeserie.text = it.numeroSerie
            txt_datadecriaçao.text = it.dataCriacao
            txt_codigoEmbalagem.text = it.codigoEmbalagem
            txt_descricaoEmbalagem.text = it.descricaoEmbalagem

            codigoBarrasAdapter?.update(it.distribuicao)
        }
    }

    private fun setVisibilityToComponents(visibility: Int) {
        include_items_codigo_barra.visibility = visibility
        rv_distribuicoes.visibility = visibility
    }

}