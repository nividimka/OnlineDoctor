package dkgroup.kz.onlinedoctor.presentation.auth

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dkgroup.kz.onlinedoctor.App
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.presentation.auth.EntryView
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.presentation.base.GlobalMenuController
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class EntryPresenter @Inject constructor(
        private val router: FlowRouter
) : BasePresenter<EntryView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}