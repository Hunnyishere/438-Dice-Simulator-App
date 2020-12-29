package com.example.cse438.cse438_assignment2

import androidx.fragment.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlinx.android.synthetic.main.show_dice_history.*
import kotlin.collections.ArrayList


class HistoryFragment : Fragment() {

    //public var listOfRolls = ArrayList<Int>()
    public var history_string : String = ""
    public var listOfRolls = ArrayList<Int>()
    public var listOfDices = ArrayList<ArrayList<Int>>()
    private var historyListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) { //view hasn't been created yet, so cannot be reached
        super.onCreate(savedInstanceState)

        //get data sent from DiceResultActivity
        val activity_intent = activity!!.intent
        listOfRolls = activity_intent!!.extras!!.getIntegerArrayList("listOfRolls")
        listOfDices = activity_intent!!.extras!!.getSerializable("listOfDices") as ArrayList<ArrayList<Int>>

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.show_dice_history, container, false)
    }

    override fun onStart() {  //lifecycle: onCreate->onCreateView(view created)->onStart
        super.onStart()

        getHistoryInitial()

        //update HistoryListAdapter to change the view displayed
        (historyListView?.adapter as? HistoryListAdapter)?.notifyDataSetChanged()
    }

    //initialize HistoryListAdapter for storing dice history data in listview
    private fun getHistoryInitial() {
        historyListView = history
        val adapter = HistoryListAdapter(activity!!, listOfRolls, listOfDices)
        historyListView?.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}