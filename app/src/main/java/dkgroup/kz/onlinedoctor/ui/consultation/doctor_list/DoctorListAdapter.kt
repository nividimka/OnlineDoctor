package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import java.util.*

class DoctorListAdapter : RecyclerView.Adapter<DoctorListViewHolder>() {
    private val items = ArrayList<Doctor>(32)
    var clickListener:ClickListener?=null
    fun swap(newList: List<Doctor>) {

        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.doctor_list_item, parent, false)
        return DoctorListViewHolder(view,clickListener)
    }

    override fun onBindViewHolder(holder: DoctorListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    public interface ClickListener{
        public fun onClick(email: String)
    }
}
