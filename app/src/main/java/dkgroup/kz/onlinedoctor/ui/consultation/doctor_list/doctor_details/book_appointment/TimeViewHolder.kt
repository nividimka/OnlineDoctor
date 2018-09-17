package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.book_appointment

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.time_item.view.*

class TimeViewHolder(itemView: View, internal var clickListener: TimeAdapter.ClickListener?) : RecyclerView.ViewHolder(itemView) {


    fun bind(time: Long, isSelected: Boolean,position:Int) {
        itemView.time.isEnabled = isSelected
        itemView.time.setText(String.format("%02d:%02d",(time/60000)/60,(time/60000)%60))
        itemView.setOnClickListener{
            if(clickListener!=null)
                clickListener!!.onClick(time,position)
        }
    }
}
