package com.surelabs.indonesia.chatapplication.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.iid.FirebaseInstanceId
import com.surelabs.indonesia.chatapplication.BaseActivity
import com.surelabs.indonesia.chatapplication.R
import com.surelabs.indonesia.chatapplication.main.ChatListActivity
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : BaseActivity() {
    private lateinit var vm: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this).get(RegisterViewModel::class.java)

        val userModel = UserModel()
        daftar.text = "PREPARING..."
        daftar.isEnabled = false
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener {
            if (it.isSuccessful) {
                userModel.token = it.result?.token
                daftar.text = "DAFTAR"
                daftar.isEnabled = true
            }
        }

        daftar.setOnClickListener {
            if (nama.text.toString().isEmpty()) {
                nama.error = "Harus Diisi"
            } else {
                daftar.text = "SENDING DATA..."
                daftar.isEnabled = false
                userModel.nama = nama.text.toString()
                vm.register(userModel)
                vm.result.observe(this) {
                    Snackbar.make(findViewById(android.R.id.content), it.message.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Menu Utama") {
                                Intent(this@RegisterActivity, ChatListActivity::class.java).apply {
                                    startActivity(this)
                                    finish()
                                }
                            }
                            .show()
                    daftar.text = "DAFTAR"
                    daftar.isEnabled = true
                }
                vm.error.observe(this) {
                    showMessage(it)
                    daftar.text = "DAFTAR"
                    daftar.isEnabled = true
                }
            }
        }
    }

    companion object {
        var TAG = RegisterActivity::class.java.simpleName
    }
}

data class UserModel(
        private val _id: Long = System.currentTimeMillis(),
        var nama: String? = null,
        var token: String? = null
)