package com.example.prelim_examapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prelim_examapp.Inquires.Inquiries


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var inqList=emptyList<Inquiries>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false)
        return MyViewHolder(v)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = inqList[position]
        holder.itemView.findViewById<TextView>(R.id.inqText).text = currentItem.inquiry.toString()

    }

    override fun getItemCount(): Int {
        return inqList.size
    }
    fun setData(user:List<Inquiries>){
        this.inqList=user
        notifyDataSetChanged()
    }
}