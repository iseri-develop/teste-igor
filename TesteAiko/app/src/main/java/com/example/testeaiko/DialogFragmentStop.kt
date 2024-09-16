package com.example.testeaiko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.testeaiko.databinding.FragmentDialogStopBinding

class DialogFragmentStop(
    val onDismiss: (String) -> Unit = {}
) : DialogFragment() {

    private var _binding: FragmentDialogStopBinding? = null
    private val binding get() = _binding

    override fun onStart() {
        super.onStart()

        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDialogStopBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchStopButton?.setOnClickListener {
            onDismiss(binding?.editSearchStop?.text.toString())
            dismiss()
        }
    }
}