package com.guresberatcan.satteliteapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guresberatcan.satteliteapp.R
import com.guresberatcan.satteliteapp.data.model.SatelliteListItem
import com.guresberatcan.satteliteapp.databinding.ItemSatelliteListBinding
import java.util.Locale

class SatelliteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClickListener: ((item: SatelliteListItem) -> Unit)? = null

    var items = listOf<SatelliteListItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            filteredList.addAll(items)
            notifyDataSetChanged()
        }

    private var filteredList = arrayListOf<SatelliteListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun filter(charText: String) {
        val newCharText = charText.lowercase(Locale.getDefault())
        filteredList.clear()
        if (newCharText.isEmpty()) {
            filteredList.addAll(items)
        } else {
            for (item in items) {
                if (item.name.lowercase(Locale.getDefault()).contains(newCharText)) {
                    filteredList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemSatelliteListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(filteredList[position])
        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }


    inner class ViewHolder(private val itemBinding: ItemSatelliteListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(satellite: SatelliteListItem) {
            itemBinding.satelliteName.text = satellite.name
            if (satellite.active) {
                Glide.with(itemBinding.root.context).load(R.drawable.drawable_satellite_active)
                    .into(itemBinding.activeImage)
                itemBinding.activeText.text =
                    itemBinding.root.context.getString(R.string.satellite_list_active)
                itemBinding.root.setOnClickListener {
                    itemClickListener?.invoke(satellite)
                }
            } else {
                Glide.with(itemBinding.root.context).load(R.drawable.drawable_satellite_deactive)
                    .into(itemBinding.activeImage)
                itemBinding.activeText.text =
                    itemBinding.root.context.getString(R.string.satellite_list_deactive)
                itemBinding.activeText.setTextColor(itemBinding.root.context.getColor(R.color.gray))
                itemBinding.satelliteName.setTextColor(itemBinding.root.context.getColor(R.color.gray))
            }
        }
    }
}