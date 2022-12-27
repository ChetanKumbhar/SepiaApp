package com.example.sepiaapp.pet.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sepiaapp.R
import com.example.sepiaapp.pet.viewModel.PetListViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity





/**
 * A fragment representing a list of Pets.
 */
@AndroidEntryPoint
class PetsListFragment : Fragment() {

    private lateinit var listAdapter: PetListAdapter
    private val petListViewModel: PetListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pet List"

        val view = inflater.inflate(R.layout.fragment_pets_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                listAdapter = PetListAdapter(context)
                adapter = listAdapter
                listAdapter.onItemClick = { it ->
                    navigateToPetDetails(it)
                }
            }
        }
        initObservers()

        return view
    }

    private fun initObservers() {
        petListViewModel.petList.observe(this.viewLifecycleOwner, { list ->
            listAdapter?.let { it.setList(list) }
        })
        petListViewModel.stateConfigTime.observe(this.viewLifecycleOwner, { isValidTime ->
            if (!isValidTime) {
                petListViewModel.showConfigAlert(activity)
            } else {
                petListViewModel.getPets()
            }
        })
    }

    private fun navigateToPetDetails(it: Int) {
        findNavController().navigate(
            R.id.action_List_to_DetailsFragment,
            Bundle().apply { putParcelable("pet", listAdapter.getItem(it)) })
    }

    override fun onResume() {
        super.onResume()
        Log.d("chetan", "onResume: PetsListFragment")
        petListViewModel.checkConfigTime()
    }
}


