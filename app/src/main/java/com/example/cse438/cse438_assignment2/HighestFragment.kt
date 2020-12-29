package com.example.cse438.cse438_assignment2

import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.highest_roll.*
import java.util.*
import kotlin.collections.ArrayList


class HighestFragment : Fragment() {

    //public var listOfRolls = ArrayList<Int>()
    public var highest : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) { //view hasn't been created yet, so cannot be reached
        super.onCreate(savedInstanceState)

        val intent = activity!!.intent
        //listOfRolls = intent!!.getIntegerArrayListExtra("listOfRolls")
        highest = intent!!.getIntExtra("highest",0)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.highest_roll, container, false)
    }

    override fun onStart() {  //lifecycle: onCreate->onCreateView(view created)->onStart
        super.onStart()

        //change view display
        highest_num.text = highest.toString()

        //get bundle sent from RollResultActivity
        var bundle = getArguments()

        //transfer data to LowestFragment to display the lowest value
        lowest_button.setOnClickListener {
            val fragment = LowestFragment()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }

        //transfer data to AverageFragment to display the average value
        average_button.setOnClickListener {
            val fragment = AverageFragment()
            fragment.arguments = bundle
            val transaction = activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }

    }
}