package com.example.cse438.cse438_assignment2

import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.lowest_roll.*
import java.util.*
import kotlin.collections.ArrayList


class LowestFragment : Fragment() {

    //public var listOfRolls = ArrayList<Int>()
    public var lowest : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = activity!!.intent
        //listOfRolls = intent!!.getIntegerArrayListExtra("listOfRolls")
        lowest = intent!!.getIntExtra("lowest",0)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lowest_roll, container, false)
    }

    override fun onStart() {
        super.onStart()

        //change view display
        lowest_num.text = lowest.toString()

        //get bundle sent from RollResultActivity
        var bundle = getArguments()

        //transfer data to HighestFragment to display the highest value
        highest_button.setOnClickListener {
            val fragment = HighestFragment()
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