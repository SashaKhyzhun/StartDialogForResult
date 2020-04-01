package com.sashakhyzhun.example.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sashakhyzhun.dialogforresult.DisplayableItem
import com.sashakhyzhun.example.R
import com.sashakhyzhun.example.model.Location

class LocationAdapter(
    val click: (DisplayableItem) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationAdapterVH>() {

    private var locations: MutableList<Location> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapterVH {
        return LocationAdapterVH(
            LayoutInflater
                .from(parent.context).inflate(R.layout.item_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationAdapterVH, position: Int) {
        val item = locations[position]

        holder.tvTitle.text = item.city
        holder.tvDescription.text = item.title

        holder.parentLayout.setOnClickListener {
            click(item)
        }
    }

    override fun getItemCount(): Int = locations.size

    fun updateLocationData(data: List<Location>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    inner class LocationAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentLayout: RelativeLayout = itemView.findViewById(R.id.item_location_parent)
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.item_location_tv_title)
        val tvDescription: AppCompatTextView = itemView.findViewById(R.id.item_location_tv_subtitle)
    }

}