package id.ac.pnm.anterinapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.ac.pnm.anterinapp.R
import id.ac.pnm.anterinapp.model.VoucherData

class VoucherAdapter(val voucherList: List<VoucherData>) :
    RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>() {

    class VoucherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvVoucherTitle)
        val tvDesc: TextView = view.findViewById(R.id.tvVoucherDesc)
        val tvDate: TextView = view.findViewById(R.id.tvVoucherDate)
        val btnUse: Button = view.findViewById(R.id.btnUseVoucher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VoucherViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_voucher, parent, false)
        return VoucherViewHolder(view)
    }

    override fun onBindViewHolder(holder: VoucherViewHolder, position: Int) {
        val item = voucherList[position]

        holder.tvTitle.text = item.title
        holder.tvDesc.text = item.description
        holder.tvDate.text = item.validUntil

        holder.btnUse.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Menggunakan ${item.title}...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return voucherList.size
    }
}