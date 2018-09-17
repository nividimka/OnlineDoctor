package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.extensions.loadRoundedImage
import kotlinx.android.synthetic.main.appointment_details_fragment.*
import kotlinx.android.synthetic.main.appointment_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class PatientsViewHolder(itemView: View, var clickListener: PatientsAdapter.ClickListener?) : RecyclerView.ViewHolder(itemView) {
    fun bind(
            patient: Patient) {
        itemView.nameTV.text = patient!!.firstName + " " + patient!!.lastName
        itemView.photoIV.loadRoundedImage("blablabal",itemView.context)
        itemView.setOnClickListener {
            if (clickListener != null) {
                clickListener!!.onClick(patient.email)
            }
        }
    }
}
