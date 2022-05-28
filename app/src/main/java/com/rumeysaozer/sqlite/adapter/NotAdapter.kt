package com.rumeysaozer.sqlite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rumeysaozer.sqlite.databinding.ItemRowBinding
import com.rumeysaozer.sqlite.model.Not
import com.rumeysaozer.sqlite.view.NotListFragmentDirections

class NotAdapter(val notList : ArrayList<Not>) : RecyclerView.Adapter<NotAdapter.NotHolder>() {
    class NotHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotHolder {
    val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NotHolder(binding)
    }

    override fun onBindViewHolder(holder: NotHolder, position: Int) {
      holder.binding.itemText.text = notList[position].baslik
        holder.itemView.setOnClickListener {
            val action = NotListFragmentDirections.actionNotListFragmentToNotUpdateFragment(notList[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notList.size
    }
}