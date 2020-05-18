package com.joaostanzione.iddog.ui.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.joaostanzione.iddog.R
import kotlinx.android.synthetic.main.item_dog_photo.view.*

class DogsAdapter(private val glide: RequestManager, private val itemClick: ItemClick) :
    RecyclerView.Adapter<DogsAdapter.DogsViewHolder>(), View.OnClickListener {

    private val dogsPhotos: ArrayList<String> = ArrayList()

    override fun onClick(view: View?) {
        val position = view?.tag as Int
        itemClick.click(dogsPhotos[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return DogsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dog_photo,
                parent,
                false
            )
        ).apply {
            setOnClick(this@DogsAdapter)
        }
    }

    override fun getItemCount(): Int = dogsPhotos.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        holder.itemView.tag = position
        dogsPhotos[position].apply {
            glide.load(this)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image)
        }
    }

    fun refresh(dogs: List<String>) {
        dogsPhotos.clear()
        dogsPhotos.addAll(dogs)
        notifyDataSetChanged()
    }

    inner class DogsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.itemDogImage

        fun setOnClick(onClick: View.OnClickListener) {
            itemView.setOnClickListener(onClick)
        }
    }
}

interface ItemClick {
    fun click(url: String)
}
