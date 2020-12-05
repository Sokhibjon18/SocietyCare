package uz.triples.societycare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.work_rv_item.view.*
import uz.triples.societycare.CallForJob
import uz.triples.societycare.R
import uz.triples.societycare.database.entities.Work


class WorkRV(val context: Context, private val callForJob: CallForJob) :
    ListAdapter<Work, WorkRV.VH>(
        DiffCallBack()
    ) {

    class DiffCallBack() : DiffUtil.ItemCallback<Work>() {
        override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.phone == newItem.phone &&
                    oldItem.address == newItem.address &&
                    oldItem.job == newItem.job &&
                    oldItem.requirement == newItem.requirement &&
                    oldItem.salary == newItem.salary
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.work_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        holder.itemView.job.text = getItem(position).job
        holder.itemView.orientation.text = getItem(position).address
        holder.itemView.salary.text = getItem(position).salary

        holder.itemView.callButton.setOnClickListener {
            if (getItem(position).phone.isNullOrEmpty())
                callForJob.call("907449596")
            else
                callForJob.call(getItem(position).phone)
        }
    }
}