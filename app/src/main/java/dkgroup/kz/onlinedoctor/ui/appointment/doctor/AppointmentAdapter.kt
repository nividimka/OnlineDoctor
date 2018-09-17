package dkgroup.kz.onlinedoctor.ui.appointment.doctor

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListViewHolder
import java.util.*

class AppointmentAdapter : RecyclerView.Adapter<AppointmentViewHolder>() {
    private val items = ArrayList<Appointment>(32)
    var clickListener:ClickListener?=null
    fun swap(newList: List<Appointment>) {

        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_list_item, parent, false)
        return AppointmentViewHolder(view,clickListener)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    public interface ClickListener{
        public fun onClick(appointmentId: String)
    }
}
