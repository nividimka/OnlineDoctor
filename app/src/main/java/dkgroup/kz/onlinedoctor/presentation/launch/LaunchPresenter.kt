package dkgroup.kz.onlinedoctor.presentation.launch

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.pubnub.api.Callback
import com.pubnub.api.Pubnub
import com.pubnub.api.PubnubError
import com.pubnub.api.PubnubException
import dkgroup.kz.onlinedoctor.Constants
import dkgroup.kz.onlinedoctor.FlowRouter
import dkgroup.kz.onlinedoctor.Screens
import dkgroup.kz.onlinedoctor.presentation.base.BasePresenter
import dkgroup.kz.onlinedoctor.ui.IncomingCallActivity
import org.json.JSONException
import org.json.JSONObject
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class LaunchPresenter @Inject constructor(
        private val router: FlowRouter,
        private val firebaseUser: FirebaseUser?
) : BasePresenter<LaunchView>() {
    var pubnub: Pubnub? = null
    private var stdByChannel: String? = null
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    fun onBackPressed() = router.finishChain()
    override fun attachView(view: LaunchView?) {
        super.attachView(view)
        if (firebaseUser != null) {
            this.stdByChannel = firebaseUser.email + Constants.STDBY_SUFFIX
            if (this.pubnub == null) {
                initPubNub()
            } else {
                subscribeStdBy()
            }
        }
    }


    fun initPubNub() {
        this.pubnub = Pubnub(Constants.PUB_KEY, Constants.SUB_KEY)
        this.pubnub!!.setUUID(this.firebaseUser!!.email)
        subscribeStdBy()
    }

    private fun subscribeStdBy() {
        try {
            this.pubnub!!.subscribe(this.stdByChannel, object : Callback() {
                override fun successCallback(channel: String?, message: Any?) {
                    Log.d("MA-iPN", "MESSAGE: " + message!!.toString())
                    if (message !is JSONObject) return  // Ignore if not JSONObject
                    val jsonMsg = message as JSONObject?
                    try {
                        if (!jsonMsg!!.has(Constants.JSON_CALL_USER)) return      //Ignore Signaling messages.
                        val user = jsonMsg.getString(Constants.JSON_CALL_USER)
                        viewState.dispatchIncomingCall(firebaseUser!!.email, user)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                }

                override fun connectCallback(channel: String?, message: Any?) {
                    Log.d("MA-iPN", "CONNECTED: " + message!!.toString())
                    setUserStatus(Constants.STATUS_AVAILABLE)
                }

                override fun errorCallback(channel: String, error: PubnubError) {
                    Log.d("MA-iPN", "ERROR: " + error.toString())
                }
            })
        } catch (e: PubnubException) {
            Log.d("HERE", "HEREEEE")
            e.printStackTrace()
        }

    }


    private fun setUserStatus(status: String) {
        try {
            val state = JSONObject()
            state.put(Constants.JSON_STATUS, status)
            this.pubnub!!.setState(this.stdByChannel, firebaseUser!!.email, state, object : Callback() {
                override fun successCallback(channel: String?, message: Any?) {
                    Log.d("MA-sUS", "State Set: " + message!!.toString())
                }
            })
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    override fun detachView(view: LaunchView?) {
        super.detachView(view)
        if (this.pubnub != null) {
            this.pubnub!!.unsubscribeAll()
        }
    }
}