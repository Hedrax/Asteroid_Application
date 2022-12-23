package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidAdabter(val clickListener: ClickListener)
    :ListAdapter<Asteroid,AsteroidAdabter.ViewHolder>(AsteroidDiffCallBack()) {



    class ViewHolder private constructor(private val binding :AsteroidItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(click:ClickListener,item : Asteroid){
            binding.asteroidProperty = item
            binding.clickListener = click
            binding.executePendingBindings()
        }

        companion object{
            fun from(viewGroup:ViewGroup):ViewHolder{

                val layoutInflater = LayoutInflater.from(viewGroup.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, viewGroup, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener,item)
    }
}
class ClickListener(val item : (lol:Asteroid)->Unit)
{
    fun onClick(newItem: Asteroid) { item(newItem)}
}

class AsteroidDiffCallBack : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
        return oldItem == newItem
    }
}
