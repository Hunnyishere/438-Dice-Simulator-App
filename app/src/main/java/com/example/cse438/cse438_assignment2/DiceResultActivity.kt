package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.show_results.*

class DiceResultActivity : AppCompatActivity() {

    public var listOfRolls = ArrayList<Int>()
    public var listOfDices = ArrayList<ArrayList<Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_results)
    }

    override fun onStart() {
        super.onStart()

        //get data from RollActivity
        var activity_bundle :Bundle ?=intent.extras
        listOfRolls = activity_bundle!!.getIntegerArrayList("listOfRolls")
        listOfDices = activity_bundle!!.getSerializable("listOfDices") as ArrayList<ArrayList<Int>>

        //display history fragment
        val fragment = HistoryFragment()
        var fragment_bundle = Bundle()
        fragment_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
        fragment_bundle.putSerializable("listOfDices", listOfDices)
        fragment.arguments = fragment_bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

        //when click "cancel" button, transfer arraylists back to MainActivity
        cancel_button.setOnClickListener{
            val home_intent = Intent(this, MainActivity::class.java)
            var home_bundle = Bundle()
            home_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
            home_bundle.putSerializable("listOfDices", listOfDices)
            home_intent.putExtras(home_bundle)
            startActivity(home_intent)
        }

    }
}