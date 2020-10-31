package com.surelabs.indonesia.chatapplication.contact.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.model.DataItem
import kotlinx.android.synthetic.main.item_adapter_contact.view.*

class AdapterContact(
    private val contactList: List<DataItem?>?,
    private val click: (DataItem?) -> Unit
) : RecyclerView.Adapter<AdapterContact.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactName = itemView.contactName

        fun onBind(item: DataItem?) {
            contactName.text = item?.nama
            itemView.setOnClickListener {
                click(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_adapter_contact, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(contactList?.get(position))
    }

    override fun getItemCount(): Int {
        return contactList?.size ?: 0
    }

}