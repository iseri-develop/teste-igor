package com.example.testeaiko.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeaiko.databinding.RowLineBinding
import com.example.testeaiko.service.model.LineModel
import com.example.testeaiko.view.viewholder.LineViewHolder

class LineAdapter : RecyclerView.Adapter<LineViewHolder>() {

    private var listLines: List<LineModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = RowLineBinding.inflate(inflater, parent, false)

        return LineViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        holder.bind(listLines[position])
    }

    override fun getItemCount(): Int {
        return listLines.count()
    }

    fun updateList(list: List<LineModel>) {
        listLines = list
        notifyDataSetChanged()
    }
}