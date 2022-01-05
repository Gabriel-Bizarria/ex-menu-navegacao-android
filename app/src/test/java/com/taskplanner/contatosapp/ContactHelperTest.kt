package com.taskplanner.contatosapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.taskplanner.contatosapp.model.Contact
import org.junit.Test
import org.robolectric.RobolectricTestRunner
import org.junit.Assert.*
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ContactHelperTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val sharedPreferences =
        context.getSharedPreferences(
            "com.taskplanner.contatosapp.PREFERENCES",
            Context.MODE_PRIVATE)

    private val contactHelper = ContactHelper(sharedPreferences)

    @Test
    fun `Quando chamar o metodo 'getListContacts()' com 2 contatos, deve retornar uma lista de 2 contatos`() {

        //Prepara
        mockListDoisContatos()

        //Valida
        val list = contactHelper.getListContacts()
        assertEquals(2, list.size)

    }

    @Test
    fun `Quando chamar o metodo 'getListContacts()' sem contatos, deve retornar uma lista vazia`(){
        //Prepara
        mockListVazia()

        //Valida
        val list = contactHelper.getListContacts()
        assertEquals(0, list.size )
    }



    private fun mockListDoisContatos(){
        contactHelper.setListContacts(
            arrayListOf(
                Contact(
                    "Gabriel Bizarria",
                    "(11)00000-0000",
                    "img1.jpg"
                ),
                Contact(
                    "Rodrigo Lima",
                    "(81)00000-0000",
                    "img2.jpg"
                )
            )
        )
    }

    private fun mockListVazia(){
        contactHelper.setListContacts(arrayListOf())
    }
}