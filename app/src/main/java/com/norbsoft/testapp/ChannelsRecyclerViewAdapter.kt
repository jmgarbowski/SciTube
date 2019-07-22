package com.norbsoft.testapp


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.norbsoft.testapp.model.Channel
import kotlinx.android.synthetic.main.item_channel.view.*

class ChannelsRecyclerViewAdapter(
    private var items: List<Channel>
) : RecyclerView.Adapter<ChannelsRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_channel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val thumbnail = item.snippet.thumbnails.medium

        Glide.with(holder.itemView)
            .load(thumbnail.url)
            .override(thumbnail.width, thumbnail.height)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.thumbnail)

        holder.title.text = item.snippet.channelTitle
        holder.description.text = item.snippet.description
    }

    override fun getItemCount(): Int = items.size

    fun addItems(items: List<Channel>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val title: TextView = item.title
        val description: TextView = item.description
        val thumbnail: ImageView = item.thumbnail
    }
}
