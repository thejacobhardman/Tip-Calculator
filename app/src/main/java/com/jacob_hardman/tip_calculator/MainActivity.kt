package com.jacob_hardman.tip_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var checkAmount : Double? = 0.00
    private var tipPercentageString : String = ""
    private var tipPercentage: Double = 0.0
    private var tipAmount : Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tip_percentage_spinner.onItemSelectedListener = this

        calculate_tip_button.setOnClickListener {
            convertValues()

            when {
                checkAmount == 0.00 -> {
                    Toast.makeText(this, "Please enter a check amount.", Toast.LENGTH_SHORT).show()
                }
                tipPercentage == 0.0 -> {
                    Toast.makeText(this, "Please select a tip percentage from the dropdown.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    tipAmount = calculateTip(checkAmount, tipPercentage)
                    val format : NumberFormat = NumberFormat.getCurrencyInstance()
                    total_tip_amount_text.text = format.format(tipAmount)

                    triggerAnimation()
                    resetUi()
                }
            }
        }
    }

    //Program Functions
    private fun convertValues() {
        checkAmount = check_amount.text.toString().toDoubleOrNull() ?: 0.00
        tipPercentage = when (tipPercentageString) {
            "5%" -> 0.05
            "10%" -> 0.1
            "15%" -> 0.15
            "20%" -> 0.2
            "25%" -> 0.25
            "30%" -> 0.3
            else -> 0.0
        }
    }

    private fun calculateTip(checkAmount: Double?, tipPercentage: Double): Double {
        val checkTotal: Double = checkAmount?.toDouble() ?: 0.00
        return checkTotal * tipPercentage
    }

    private fun triggerAnimation() {
        val fadeAnimation = AnimationUtils.loadAnimation(this, R.anim.fade)
        total_tip_amount_text.startAnimation(fadeAnimation)
    }

    private fun resetUi() {
        check_amount.text.clear()
        tip_percentage_spinner.setSelection(0)
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

    //Spinner Functions
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        tipPercentageString = tip_percentage_spinner.selectedItem as String
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
