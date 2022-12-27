package com.example.sepiaapp.pet.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sepiaapp.databinding.FragmentPetsListItemBinding
import com.example.sepiaapp.helper.GlideHelper
import com.example.sepiaapp.model.Pet

/**
 * [RecyclerView.Adapter] that can display a [ListItem].
 * TODO: Replace the implementation with code for your data type.
 */
class PetListAdapter(val context: Context) :
    RecyclerView.Adapter<PetListAdapter.ViewHolder>() {
    private var values: List<Pet> = listOf()
    var onItemClick: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentPetsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setPetItem(holder, position)
    }

    private fun setPetItem(holder: ViewHolder, position: Int) {
        val pet = values[position]
        holder.titlePet.text = pet.title
        GlideHelper.loadImage(holder.imagePet, pet.imageUrl, false)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPetsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        internal val imagePet: ImageView = binding.imagePet
        internal val titlePet: TextView = binding.titlePet

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(layoutPosition)
            }
        }
    }

    fun getItem(index: Int): Pet {
        return values[index]
    }

    fun setList(list: List<Pet>) {
        values = list
    }

}