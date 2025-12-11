package id.ac.pnm.anterinapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import id.ac.pnm.anterinapp.model.CombinationData
import id.ac.pnm.anterinapp.R

class CombinationAdapter(
    private val items: List<CombinationData>,
    private val onItemClick: (CombinationData) -> Unit
) : RecyclerView.Adapter<CombinationAdapter.ViewHolder>() {

    private var selectedPosition = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardRoot: MaterialCardView = itemView.findViewById(R.id.cardRoot)
        val ivTrans1: ImageView = itemView.findViewById(R.id.ivTrans1)
        val tvTrans1: TextView = itemView.findViewById(R.id.tvTrans1)
        val ivTrans2: ImageView = itemView.findViewById(R.id.ivTrans2)
        val tvTrans2: TextView = itemView.findViewById(R.id.tvTrans2)

        fun bind(item: CombinationData, position: Int) {
            tvTrans1.text = item.transport1Name
            ivTrans1.setImageResource(item.transport1Icon)

            tvTrans2.text = item.transport2Name
            ivTrans2.setImageResource(item.transport2Icon)

            if (selectedPosition == position) {
                cardRoot.strokeWidth = 4
                cardRoot.setCardBackgroundColor(Color.parseColor("#F0F4FF"))
            } else {
                cardRoot.strokeWidth = 0
                cardRoot.setCardBackgroundColor(Color.WHITE)
            }

            itemView.setOnClickListener {
                val previousItem = selectedPosition
                selectedPosition = position

                notifyItemChanged(previousItem)
                notifyItemChanged(selectedPosition)

                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_combination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size
}