package dkgroup.kz.onlinedoctor.ui.doctor_consultation.patients.details.doctor_note

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.note_item.view.*
import java.text.SimpleDateFormat

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
            note: Note) {
        itemView.noteTV.text = note.text
        var df = SimpleDateFormat("dd-MM-yyyy")
        itemView.dateTV.text = df.format(note.date)
    }
}