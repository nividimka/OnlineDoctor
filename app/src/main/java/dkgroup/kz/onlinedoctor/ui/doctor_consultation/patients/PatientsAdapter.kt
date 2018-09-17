package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dkgroup.kz.onlinedoctor.R
import java.util.*

class PatientsAdapter : RecyclerView.Adapter<PatientsViewHolder>() {
    private val items = ArrayList<Patient>(32)
    var clickListener:ClickListener?=null
    fun swap(newList: List<Patient>) {

        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.patient_list, parent, false)
        return PatientsViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    public interface ClickListener{
        public fun onClick(email: String)
    }
}
