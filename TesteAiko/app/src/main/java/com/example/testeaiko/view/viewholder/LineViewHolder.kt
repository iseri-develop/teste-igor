package com.example.testeaiko.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.testeaiko.databinding.RowLineBinding
import com.example.testeaiko.service.model.LineModel

class LineViewHolder(private val binding: RowLineBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(line: LineModel) {
        binding.textPrincipalTerminal.text = "${line.firstLetterLine}, FROM: ${line.terminalPrimary}"
        binding.textSecondaryTerminal.text = "TO: ${line.terminalSecondary}"

        if (line.lineCircle) {
            binding.textCircularMode.text = "CIRCULAR: YES"
        } else {
            binding.textCircularMode.text = "CIRCULAR: NO"
        }
    }
}