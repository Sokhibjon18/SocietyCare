package uz.triples.societycare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.poor_rv_item.view.*
import uz.triples.societycare.*
import uz.triples.societycare.ShareMoney
import uz.triples.societycare.database.entities.PoorlySupplied


class SocietyRV(val context: Context, private val shareMoney: ShareMoney) : ListAdapter<PoorlySupplied, SocietyRV.VH>(
    DiffCallBack()
) {

    class DiffCallBack() : DiffUtil.ItemCallback<PoorlySupplied>() {
        override fun areItemsTheSame(oldItem: PoorlySupplied, newItem: PoorlySupplied): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: PoorlySupplied, newItem: PoorlySupplied): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.poor_rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var count = 0
        var canWork = 0
        val itemView = holder.itemView
        val item = getItem(position)
        itemView.nameOfTheFamily.text = "${item.name}lar oilasi"
        when (item.father) {
            "Imkoniyati cheklangan" -> {
                itemView.man.setImageResource(R.drawable.ic_wheelchair)
                count++
            }
            "-" -> {
                itemView.man.visibility = View.GONE
            }
            "Mehnatga layoqatli, ishsiz" -> {
                itemView.woman.setImageResource(R.drawable.ic_woman)
                canWork++
            }
            else -> {
                itemView.man.setImageResource(R.drawable.ic_man)
                count++
            }
        }

        when (item.mother) {
            "Imkoniyati cheklangan" -> {
                itemView.woman.setImageResource(R.drawable.ic_wheelchair)
                count++
            }
            "-" -> {
                itemView.woman.visibility = View.GONE
            }
            "Mehnatga layoqatli, ishsiz" -> {
                itemView.woman.setImageResource(R.drawable.ic_woman)
                canWork++
            }
            else -> {
                itemView.woman.setImageResource(R.drawable.ic_woman)
                count++
            }
        }

        when(item.memberCount-count){
            0 -> {
                itemView.child1.visibility = View.GONE
                itemView.child2.visibility = View.GONE
                itemView.child3.visibility = View.GONE
                itemView.child4.visibility = View.GONE
                itemView.child5.visibility = View.GONE
            }
            1 -> {
                itemView.child1.visibility = View.VISIBLE
                itemView.child2.visibility = View.GONE
                itemView.child3.visibility = View.GONE
                itemView.child4.visibility = View.GONE
                itemView.child5.visibility = View.GONE
            }
            2 -> {
                itemView.child1.visibility = View.VISIBLE
                itemView.child2.visibility = View.VISIBLE
                itemView.child3.visibility = View.GONE
                itemView.child4.visibility = View.GONE
                itemView.child5.visibility = View.GONE
            }
            3 -> {
                itemView.child1.visibility = View.VISIBLE
                itemView.child2.visibility = View.VISIBLE
                itemView.child3.visibility = View.VISIBLE
                itemView.child4.visibility = View.GONE
                itemView.child5.visibility = View.GONE
            }
            4 -> {
                itemView.child1.visibility = View.VISIBLE
                itemView.child2.visibility = View.VISIBLE
                itemView.child3.visibility = View.VISIBLE
                itemView.child4.visibility = View.VISIBLE
                itemView.child5.visibility = View.GONE
            }
            5 -> {
                itemView.child1.visibility = View.VISIBLE
                itemView.child2.visibility = View.VISIBLE
                itemView.child3.visibility = View.VISIBLE
                itemView.child4.visibility = View.VISIBLE
                itemView.child5.visibility = View.VISIBLE
            }
        }

        itemView.canWork.text = canWork.toString()

        holder.itemView.mainCard.setOnClickListener {
            shareMoney.sendMoney(getItem(position))
        }
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            0,
            item.sum/3000000
        )
        itemView.progress.layoutParams = param
    }
}