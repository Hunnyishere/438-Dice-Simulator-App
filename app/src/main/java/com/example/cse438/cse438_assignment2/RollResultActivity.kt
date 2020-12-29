package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.show_results.*

class RollResultActivity : AppCompatActivity() {

    public var listOfRolls = ArrayList<Int>()
    public var listOfDices = ArrayList<ArrayList<Int>>()
    public var highest : Int = 0
    public var lowest : Int = 0
    public var average : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_results)
    }

    override fun onStart() {
        super.onStart()

        //get data from RollActivity
        var activity_bundle :Bundle ?=intent.extras
        highest = activity_bundle!!.getInt("highest")
        lowest = activity_bundle!!.getInt("lowest")
        average = activity_bundle!!.getInt("average")
        listOfRolls = activity_bundle!!.getIntegerArrayList("listOfRolls")
        listOfDices = activity_bundle!!.getSerializable("listOfDices") as ArrayList<ArrayList<Int>>

        //first display highest fragment, transfer all three statistics to HighestFragment
        // so that it can transfer to other two activities
        val fragment = HighestFragment()
        var fragment_bundle = Bundle()
        fragment_bundle.putInt("highest", highest)
        fragment_bundle.putInt("lowest", lowest)
        fragment_bundle.putInt("average", average)
        fragment.arguments = fragment_bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

        //When click "cancel" button, transfer arraylists data back to MainActivity
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