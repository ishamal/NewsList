package com.example.myapplication.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.DialogSortBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SortDialog(var listener : FilterClickListener) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogSortBinding
    private var selectedFilter : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSortBinding.inflate(inflater)

        binding.chipHealth.setOnClickListener {
            selectedFilter = "relevancy"
        }

        binding.chipTechnology.setOnClickListener {
            selectedFilter = "popularity"
        }

        binding.chipBusiness.setOnClickListener {
            selectedFilter = "publishedAt"
        }

        binding.chipReset.setOnClickListener {
            selectedFilter = null
            binding.chipHealth.isChecked = false
            binding.chipTechnology.isChecked = false
            binding.chipBusiness.isChecked = false
        }

        binding.saveButton.setOnClickListener {
            listener.onSearchClicked(selectedFilter)
            dismiss()
        }

        return binding.root
    }

}

interface FilterClickListener{
    fun onSearchClicked(filter : String?)
}