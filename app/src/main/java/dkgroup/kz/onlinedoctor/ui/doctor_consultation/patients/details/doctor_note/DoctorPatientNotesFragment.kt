package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.android.support.AndroidSupportInjection
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.doctor_note.DoctorPatientNotesPresenter
import dkgroup.kz.onlinedoctor.presentation.doctor_consultation.patients.details.doctor_note.DoctorPatientNotesView
import kotlinx.android.synthetic.main.doctor_patient_notes_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class DoctorPatientNotesFragment : MvpAppCompatFragment(), DoctorPatientNotesView {
    override fun clearEt() {
        noteET.setText("")
    }

    override fun showNotes(notes: List<Note>) {
        adapter.swap(notes)
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: DoctorPatientNotesPresenter

    @ProvidePresenter
    fun providePresenter(): DoctorPatientNotesPresenter {
        return presenter
    }


    internal lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        presenter.email = arguments.getString("email")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.doctor_patient_notes_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { presenter.onBackClick() }
        adapter = NoteAdapter()
        noteRV.adapter = adapter
        addNote.setOnClickListener {
            if (noteET.text.toString() == "") {
                noteET.error = "Для добавления заметки введите текст"
                return@setOnClickListener
            }
            presenter.addNote(noteET.text.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {

        fun newInstance(args: Bundle): DoctorPatientNotesFragment {
            val fragment = DoctorPatientNotesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
    }

}
