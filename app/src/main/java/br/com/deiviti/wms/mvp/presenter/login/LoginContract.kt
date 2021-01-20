package br.com.deiviti.wms.mvp.presenter.login

interface LoginContract {

    interface View {
        fun showServerError()
        fun initializeArmazemListActivity(token: String)
        fun showErrorMessage(errorMessage: String)
    }

    interface Presenter {
        fun postLogin(userName: String, password: String)
    }

}
