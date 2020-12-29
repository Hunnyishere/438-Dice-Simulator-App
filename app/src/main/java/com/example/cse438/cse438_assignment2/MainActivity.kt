package com.example.cse438.cse438_assignment2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    public var listOfRolls = ArrayList<Int>()
    public var listOfDices = ArrayList<ArrayList<Int>>()
    public var num_of_dice : Int = 0
    public var max_dice_value : Int = 0
    public lateinit var rollButton : Button
    public lateinit var clearButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        var bundle :Bundle ?=intent.extras
        if(bundle!=null){
            listOfRolls = bundle!!.getIntegerArrayList("listOfRolls")
            listOfDices = bundle!!.getSerializable("listOfDices") as ArrayList<ArrayList<Int>>
        }

        //set views
        rollButton = roll_button
        clearButton = clear_button

        //when click "roll" button, create an intent and transfer data to RollActivity
        roll_button.setOnClickListener {
            var num_dice_text = num_dice_input.text.toString()
            var max_dice_text = max_dice_input.text.toString()
            //check if user input valid values, if not, alert them
            if(num_dice_text == "" || max_dice_text==""){
                val myToast = Toast.makeText(this, "Please enter valid values.", Toast.LENGTH_SHORT)
                myToast.show()
            }
            else {
                num_of_dice = num_dice_text.toInt()
                max_dice_value = max_dice_text.toInt()
                if (num_of_dice < 1 || max_dice_value < 1) {
                    val myToast =
                        Toast.makeText(this, "Values should be greater than 0.", Toast.LENGTH_SHORT)
                    myToast.show()
                }
                else {
                    val roll_intent = Intent(this, RollActivity::class.java)
                    var bundle = Bundle()
                    bundle.putInt("num_of_dice", num_of_dice)
                    bundle.putInt("max_dice_value", max_dice_value)
                    bundle.putIntegerArrayList("listOfRolls", listOfRolls)
                    bundle.putSerializable("listOfDices", listOfDices)
                    roll_intent.putExtras(bundle)
                    startActivity(roll_intent)
                }
            }
        }

        clear_button.setOnClickListener {
            clearAlert()
        }
    }

    //alert users before clearing the arraylist
    private fun clearAlert() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.alert, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("You're going to clear all dice rolling history!")
        val mAlertDialog = mBuilder.show()

        //If click "ok" button, then clear both roll and dice arraylists, if click "cancel", just dismiss the dialog box
        mAlertDialog.ok_alert_button.setOnClickListener {
            mAlertDialog.dismiss()
            listOfRolls.clear()
            for(i in 0 until listOfDices.size){
                listOfDices[i].clear()
            }
            listOfDices.clear()
        }
        mAlertDialog.cancel_alert_button.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

}
