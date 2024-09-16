package com.example.testeaiko.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeaiko.R
import com.example.testeaiko.databinding.ActivityLineBinding
import com.example.testeaiko.view.adapter.LineAdapter
import com.example.testeaiko.viewmodel.LineViewModel

class LineActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLineBinding
    private lateinit var viewModel: LineViewModel

    private val adapter = LineAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LineViewModel::class.java]

        //Configura os listeners
        binding.searchButton.setOnClickListener(this)
        configureRecycler()

        observe()
    }

    private fun configureRecycler() {
        binding.recyclerLines.layoutManager = LinearLayoutManager(this)
        binding.recyclerLines.adapter = adapter
    }

    private fun observe() {
        viewModel.lines.observe(this) {
            adapter.updateList(it)
        }
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.search_button) {
            if (binding.editSearchLines.text.toString().isNotEmpty()) {
                viewModel.listLines(binding.editSearchLines.text.toString())
            } else {
                binding.editSearchLines.error = getString(R.string.write_line)
            }
        }
    }
}