package com.example.lab_activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

//    var userName: EditText? = null
//    var password: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var def_UserName = "admin"
        var def_Password = "admin"

        val userName = findViewById(R.id.et_uname) as EditText
        val passWord = findViewById(R.id.et_pword) as EditText

        findViewById<Button>(R.id.loginbtn).setOnClickListener {
            val uname: String = userName.text.toString()
            val pword: String = passWord.text.toString()
            if(uname == def_UserName){
                if(pword == def_Password){
                    login_btn()
                }
                else{
                    Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Username does not exist", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun login_btn(){
        val intent = Intent(this, Options::class.java)
        startActivity(intent)
    }
}