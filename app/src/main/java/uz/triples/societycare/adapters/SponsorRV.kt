package uz.triples.societycare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sponsoring_rv_item.view.*
import kotlinx.android.synthetic.main.work_rv_item.view.*
import uz.triples.societycare.R
import uz.triples.societycare.database.entities.Sponsoring
import uz.triples.societycare.database.entities.Work

class SponsorRV(val context: Context, private val callForJob: uz.triples.societycare.Sponsoring) :
    ListAdapter<Sponsoring, SponsorRV.VH>(
        DiffCallBack()
    ) {

    class DiffCallBack() : DiffUtil.ItemCallback<Sponsoring>() {
        override fun areItemsTheSame(oldItem: Sponsoring, newItem: Sponsoring): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: Sponsoring, newItem: Sponsoring): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.sponsoring_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.nameOrg.text = getItem(position).name
        holder.itemView.addressOrg.text = getItem(position).address
        holder.itemView.directorOrg.text = getItem(position).director
        holder.itemView.numberOrg.text = "Tel: ${getItem(position).phone}"
        holder.itemView.sponsoringCard.setOnClickListener {
            callForJob.call(getItem(position))
        }
    }
}