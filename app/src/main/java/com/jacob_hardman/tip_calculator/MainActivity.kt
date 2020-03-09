package com.jacob_hardman.tip_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //About Menu Functions
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_item) {
            showAboutInfo()
        }
        return true
    }

    private fun showAboutInfo() {
        val aboutTitle = getString(R.string.about_title)
        val aboutMessage = getString(R.string.about_message)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(aboutTitle)
        builder.setMessage(aboutMessage)
        builder.create().show()
    }
}
