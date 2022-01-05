package com.taskplanner.contatosapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.taskplanner.contatosapp.ContactDetail.Companion.EXTRA_CONTACT
import com.taskplanner.contatosapp.model.Contact

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    //Representação da recycler view
    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }

    //Instância do Adapter de contatos
    private val adapter = ContactAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_menu)

        initDrawer()
        fetchListContact()
        bindViews()
        //throw NullPointerException()
    }

    private fun fetchListContact(){
        val list = arrayListOf(
            Contact(
                "Gabriel Bizarria",
                "(11)00000-0000",
                "img1.png"
            ),
            Contact(
                "Rodrigo Lima",
                "(11)00000-0000",
                "img2.png"
            )
        )
        getInstanceSharedPreferences().edit() {
            putString("contacts", Gson().toJson(list))
        }
    }

    private fun getInstanceSharedPreferences() : SharedPreferences{
        return getSharedPreferences(
            "com.taskplanner.contatosapp.PREFERENCES",
            Context.MODE_PRIVATE
        )
    }

    //Inicia o menu lateral (ou Drawer)
    private fun initDrawer(){
        val drawerLayout = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    //Vincula todos os dados do adapter a parte da interface da aplicação
    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        updateList()
    }

    private fun getListContacts(): List<Contact>{
        val list = getInstanceSharedPreferences().getString("contacts", "[]")
        val turnsType = object : TypeToken<List<Contact>>() {}.type
        return Gson().fromJson(list, turnsType)
    }

    //Dados de uma lista mockada
    private fun updateList(){
        adapter.updateList(getListContacts())
    }

    //Configura um padrão de toast
    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //Infla o menu de opções da aplicação
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    //Define os eventos quando o usuário toca em alguma das duas opções do menu "Opções"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu1 ->{
                Log.i("Menu 1", "Clicou no menu 1")
                showToast("Você clicou no menu 1")
                true
            }

            R.id.item_menu2 ->{
                Log.i("Menu 2", "Clicou no menu 2")
                showToast("Você clicou no menu 2")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun clickItemContact(contact: Contact) {
        val intent = Intent(this, ContactDetail::class.java)
        intent.putExtra(EXTRA_CONTACT, contact)
        startActivity(intent)
    }
}