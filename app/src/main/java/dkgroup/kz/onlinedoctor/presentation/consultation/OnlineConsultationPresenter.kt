package dkgroup.kz.onlinedoctor.presentation.consultation

import com.arellomobile.mvp.InjectViewState
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.presentation.base.GlobalMenuController
import javax.inject.Inject

@InjectViewState
class OnlineConsultationPresenter @Inject constructor(
        private val router: FlowRouter
) : BasePresenter<OnlineConsultationView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }


    fun onMenuClick() = App.INSTANCE.menuController.open()
}