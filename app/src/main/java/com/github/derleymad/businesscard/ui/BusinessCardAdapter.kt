package com.github.derleymad.businesscard.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.derleymad.businesscard.data.BusinessCard
import com.github.derleymad.businesscard.databinding.ListItemBusinessCardBinding

class BusinessCardAdapter :
    ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {
    var cardClickListener: (View) -> Unit = {}
    var shareListener: (View) -> Unit = {}
    var deleteListener: (View, BusinessCard) -> Unit = { _, _: BusinessCard -> }
    var editListener: (View, BusinessCard) -> Unit = { _, _: BusinessCard -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val listItemBinding = ListItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(listItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val listItemBinding: ListItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(item: BusinessCard) {
            try {
                val color = Color.parseColor(item.customBackground)
                listItemBinding.cvCard.setCardBackgroundColor(color)
                listItemBinding.btnEdit.setBackgroundColor(color)
                listItemBinding.btnShare.setBackgroundColor(color)
                listItemBinding.btnDelete.setBackgroundColor(color)
            } catch (err: Exception) {
                Log.e("CARD_ADAPTER", err.message.toString())
            } catch (err: IllegalArgumentException) {
                Log.e("CARD_ADAPTER", err.message.toString())
            }
            listItemBinding.tvName.text = item.name
            listItemBinding.tvPhone.text = item.phone
            listItemBinding.tvEmail.text = item.email
            listItemBinding.tvCompany.text = item.company
            listItemBinding.btnShare.setOnClickListener {
                shareListener(listItemBinding.cvCard)
            }
            listItemBinding.cvCard.setOnClickListener {
                cardClickListener(it)
            }
            listItemBinding.btnDelete.setOnClickListener {
                deleteListener(listItemBinding.cvCard, item)
            }
            listItemBinding.btnEdit.setOnClickListener {
                editListener(listItemBinding.cvCard, item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.email == newItem.email &&
                oldItem.company == newItem.company &&
                oldItem.customBackground == newItem.customBackground
    }

    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.email == newItem.email &&
                oldItem.company == newItem.company &&
                oldItem.customBackground == newItem.customBackground
    }

}