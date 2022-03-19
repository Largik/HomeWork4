package com.test.homework6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val rootView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_item, parent, false)

        return ContactViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position])
    }
}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textNameView: TextView = itemView.findViewById(R.id.nameId)
    private val textNumberView: TextView = itemView.findViewById(R.id.numberId)

    fun bind(contact: Contact) {
        textNameView.text = contact.name
        textNumberView.text = contact.number
    }
}