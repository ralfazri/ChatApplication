package com.surelabs.indonesia.chatapplication.contact

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.surelabs.indonesia.chatapplication.BaseActivity
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.chatapplication.chat.ChatActivity
import com.surelabs.indonesia.chatapplication.contact.adapter.AdapterContact
import com.surelabs.indonesia.model.DataItem
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : BaseActivity() {
    private lateinit var vm: ContactViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        supportActionBar?.apply {
            title = "Daftar Kontak"
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        vm = ViewModelProvider(this).get(ContactViewModel::class.java)

        vm.getContactList()
        vm.result.observe(this) {
            setToList(it.data)
        }
        vm.error.observe(this) {
            showMessage(it)
        }
    }

    private fun setToList(data: List<DataItem?>?) {
        val adapter = AdapterContact(data) {
            Intent(this@ContactActivity, ChatActivity::class.java).apply {
                putExtra("contact", it)
                startActivity(this)
                finish()
            }
        }
        rvContact.adapter = adapter
        rvContact.layoutManager = LinearLayoutManager(this)

    }
}