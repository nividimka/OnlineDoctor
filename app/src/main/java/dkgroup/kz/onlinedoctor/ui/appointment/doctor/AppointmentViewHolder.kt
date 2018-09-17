package dkgroup.kz.onlinedoctor.ui.appointment.doctor

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.extensions.loadRoundedImage
import kotlinx.android.synthetic.main.appointment_details_fragment.*
import kotlinx.android.synthetic.main.appointment_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class AppointmentViewHolder(itemView: View, var clickListener: AppointmentAdapter.ClickListener?) : RecyclerView.ViewHolder(itemView) {
    fun bind(
            appointment: Appointment) {
        itemView.nameTV.text = appointment.user!!.firstName + " " + appointment.user!!.lastName
        itemView.photoIV.loadRoundedImage("qweqqwe", itemView.context)
        itemView.specialityTV.text = "Doctor"
        itemView.reviewTV.text = "12 reviews"
        itemView.ratingTV.text = "4.0"

        var df = SimpleDateFormat("dd MMMM, EEEE", Locale.US)
        itemView.dateTV.text = String.format("%s, %02d:%02d", df.format(appointment.date), (appointment.time!! / 60000) / 60, (appointment.time!! / 60000) % 60)
        if (appointment.status == "new")
            itemView.dateTV.setTextColor(Color.parseColor("#0076BF"))
        else if (appointment.status == "completed")
            itemView.dateTV.setTextColor(Color.parseColor("#4CAF50"))
        else if (appointment.status == "cancel")
            itemView.dateTV.setTextColor(Color.parseColor("#F44336"))
        else
            itemView.dateTV.setTextColor(Color.GRAY)
        itemView.setOnClickListener {
            if (clickListener != null) {
                clickListener!!.onClick(appointment.appointmentId)
            }
        }
    }
}
