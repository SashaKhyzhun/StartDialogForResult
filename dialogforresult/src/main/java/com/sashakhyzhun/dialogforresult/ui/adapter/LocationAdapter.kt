package com.sashakhyzhun.dialogforresult.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sashakhyzhun.dialogforresult.R
import com.sashakhyzhun.dialogforresult.data.DisplayableItem
import com.sashakhyzhun.dialogforresult.data.model.PlainData

class LocationAdapter(
    val click: (DisplayableItem) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationAdapterVH>() {

    private var locations: MutableList<PlainData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapterVH {
        return LocationAdapterVH(
            LayoutInflater
                .from(parent.context).inflate(R.layout.item_location, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationAdapterVH, position: Int) {
        val item = locations[position]

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description

        item.icon?.let {
            holder.ivLeft.setImageResource(it)
        }

        holder.parentLayout.setOnClickListener {
            click(item)
        }
    }

    override fun getItemCount(): Int = locations.size

    fun updateLocationData(data: List<PlainData>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    inner class LocationAdapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parentLayout: RelativeLayout = itemView.findViewById(R.id.item_location_parent)
        val tvTitle: AppCompatTextView = itemView.findViewById(R.id.item_location_tv_title)
        val ivLeft: AppCompatImageView = itemView.findViewById(R.id.item_location_iv_left)
        val tvDescription: AppCompatTextView = itemView.findViewById(R.id.item_location_tv_subtitle)
    }

}