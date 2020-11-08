package com.example.lab_activity

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val REQUEST_CODE = 42
class Options : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)

        findViewById<Button>(R.id.calculator).setOnClickListener { calculator() }

        val site:String = "www.scribblehub.com"
        findViewById<Button>(R.id.browser).setOnClickListener { browser(site) }

        findViewById<Button>(R.id.camera).setOnClickListener { photo() }

        findViewById<Button>(R.id.images).setOnClickListener { images() }

        findViewById<Button>(R.id.settings).setOnClickListener { openSettings() }
        
    }
    
    private fun calculator(){
        val calc = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_CALCULATOR)
        if(calc.resolveActivity(packageManager) != null){
            startActivity(calc)
        }
        else{
            Toast.makeText(this, "No Activity found to handle Intent", Toast.LENGTH_SHORT).show()
        }
    }

    private fun browser(query: String) {
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, query)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun photo() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(this.packageManager) != null){
            startActivityForResult(intent, REQUEST_CODE)
        }
        else{
            Toast.makeText(this, "Service unavailable", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image = findViewById(R.id.imageView) as ImageView
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val takenPic = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(takenPic)
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    fun images() {
        val getPic = Intent(Intent.ACTION_GET_CONTENT)
        getPic.setType("images")// -> image/*
        if(getPic.resolveActivity(this.packageManager) != null){
            startActivity(getPic)
        }
        else{
            Toast.makeText(this, "Unexpected error occured", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}