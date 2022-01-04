package com.taskplanner.contatosapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.taskplanner.contatosapp.model.Contact

class ContactAdapter(var listener: ClickItemContactListener):
    RecyclerView.Adapter<ContactAdapterViewHolder>() {

    //Inicia uma lista de contatos
    private val contactList: MutableList<Contact> = mutableListOf()

    //Criação do ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.contact_item,
            parent,
            false
        )
        return ContactAdapterViewHolder(view, contactList, listener)

    }

    //Vinculação dos dados ao ViewHolder
    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    //Retorna o tamanho da lista (número de itens da lista)
    override fun getItemCount(): Int {
        return contactList.size
    }

    //Função do adapter, para atualizar a lista -> Sendo usada dentro do método de mesmo nome
    //dentro da Main Activity
    fun updateList(list: List<Contact>){
        this.contactList.clear()
        this.contactList.addAll(list)
        notifyDataSetChanged()
    }
}

//Cria o ViewHolder
class ContactAdapterViewHolder(
    itemView: View,
    var list: List<Contact>,
    var listener: ClickItemContactListener
    ): RecyclerView.ViewHolder(itemView){

    private val tvName: TextView = itemView.findViewById(R.id.tv_name)
    private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
    private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)

    init{
        itemView.setOnClickListener {
            listener.clickItemContact(list[adapterPosition])
        }
    }

    fun bind(contact: Contact){
        tvName.text = contact.name
        tvPhone.text = contact.phone
    }
}