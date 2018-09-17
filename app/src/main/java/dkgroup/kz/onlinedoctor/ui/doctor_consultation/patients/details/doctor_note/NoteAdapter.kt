package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dkgroup.kz.onlinedoctor.R
import dkgroup.kz.onlinedoctor.entity.model.Doctor
import dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.DoctorListViewHolder
import java.util.*

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    private val items = ArrayList<Note>(32)
    fun swap(newList: List<Note>) {

        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
