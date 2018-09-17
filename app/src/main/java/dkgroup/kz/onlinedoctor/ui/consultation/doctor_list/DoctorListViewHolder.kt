package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list

import android.support.v7.widget.RecyclerView
import android.view.View
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.extensions.loadRoundedImage
import kotlinx.android.synthetic.main.doctor_list_item.view.*

class DoctorListViewHolder(itemView: View, var clickListener: DoctorListAdapter.ClickListener?) : RecyclerView.ViewHolder(itemView) {
    fun bind(
            doctor: Doctor) {
        itemView.nameTV.text = doctor.name + " " + doctor.surname
        itemView.photoIV.loadRoundedImage("qweqqwe", itemView.context)
        itemView.specialityTV.text = "Doctor"
        itemView.reviewTV.text = "12 reviews"
        itemView.ratingTV.text = "4.0"
        itemView.setOnClickListener {
            if (clickListener != null) {
                clickListener!!.onClick(doctor.email)
            }
        }
    }
}
