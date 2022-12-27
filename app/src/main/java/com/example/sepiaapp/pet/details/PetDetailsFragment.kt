package com.example.sepiaapp.pet.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sepiaapp.databinding.FragmentPetDetailsBinding
import com.example.sepiaapp.helper.GlideHelper
import com.example.sepiaapp.helper.WebViewHelper
import com.example.sepiaapp.model.Pet
import com.example.sepiaapp.pet.viewModel.PetListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *  fragment showing pet details.
 */
@AndroidEntryPoint
class PetDetailsFragment : Fragment() {
    private var pet: Pet? = null
    private var _binding: FragmentPetDetailsBinding? = null
    private val binding get() = _binding!!
    private val petListViewModel : PetListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pet = it.getParcelable("pet")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pet Details"
        _binding = FragmentPetDetailsBinding.inflate(inflater, container, false)
        initObservers()
        return binding?.root
    }

    private fun initObservers() {
        petListViewModel.stateConfigTime.observe(this.viewLifecycleOwner, { isValidTime ->
            if (!isValidTime) {
                petListViewModel.showConfigAlert(activity)
            } else {
                pet?.let {  loadData(it) }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Log.d("chetan", "onResume: PetDetailsFragment")
        petListViewModel.checkConfigTime()
    }

    private fun loadData(petData: Pet) {
        binding.tvTitle.text = petData.title
        binding.tvTime.text = "data updated on \n"+petData.date.toString()
        binding.tvDetailLabel.text = "Check details for ${petData.title}  below,"
        GlideHelper.loadImage(binding.imageView,petData.imageUrl,true)
        WebViewHelper.load(binding.webViewDetails,petData.contentUrl,binding.progressBar)
    }
}