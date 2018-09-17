package dkgroup.kz.onlinedoctor.ui.consultation.doctor_list.doctor_details.book_appointment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dkgroup.kz.onlinedoctor.R
import java.util.*

class TimeAdapter() : RecyclerView.Adapter<TimeViewHolder>() {
    var selected = -1

    private val items = ArrayList<Long>(32)
    fun swap(newList: List<Long>) {
        items.clear()
        selected = -1
        items.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.time_item, parent, false)
        return TimeViewHolder(view, object:ClickListener{
            override fun onClick(time: Long,position:Int) {
                if(clickListener!=null){
                    clickListener!!.onClick(time,position)
                }
                var oldSelected = selected
                selected = position
                notifyItemChanged(selected)
                notifyItemChanged(oldSelected)
            }

        })
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(items.get(position),position == selected,position)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    var clickListener: ClickListener? = null

    interface ClickListener {
        public fun onClick(time: Long,position: Int)
    }
}
