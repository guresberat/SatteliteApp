package com.guresberatcan.satteliteapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guresberatcan.satteliteapp.R
import com.guresberatcan.satteliteapp.data.model.SatelliteListItem
import com.guresberatcan.satteliteapp.databinding.ItemSatelliteListBinding

class SatelliteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClickListener: ((item: SatelliteListItem) -> Unit)? = null

    var items = listOf<SatelliteListItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
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
            is ViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}