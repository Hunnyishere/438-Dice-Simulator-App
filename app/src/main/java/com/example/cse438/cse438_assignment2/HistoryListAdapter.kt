package com.example.cse438.cse438_assignment2

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

class HistoryListAdapter(private var activity: Activity, private var listOfRolls: ArrayList<Int>, private var listOfDices: ArrayList<ArrayList<Int>>): BaseAdapter() {
//each ArrayList retrieve one column
    /**
     * Need to override the ViewHolder method
     */
    private class ViewHolder(row: View?){
        var diceId: TextView? = null
        var diceSum: TextView? = null
        var diceEach: TextView? = null
        var diceHighest: TextView? = null
        var diceLowest: TextView? = null
        var diceAverage: TextView? = null

        init {
            this.diceId = row?.findViewById(R.id.dice_id)
            this.diceSum = row?.findViewById(R.id.dice_sum)
            this.diceEach = row?.findViewById(R.id.dice_each)
            this.diceHighest = row?.findViewById(R.id.dice_highest)
            this.diceLowest = row?.findViewById(R.id.dice_lowest)
            this.diceAverage = row?.findViewById(R.id.dice_average)
        }
    }

    /**
     * Displays the information in the row format we want
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // the position returns specific chosen item from the whole list
        val view: View?  //name: type
        val viewHolder: ViewHolder
        if (convertView == null) {  //hasn't been created
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.history_listview, null)
            viewHolder = ViewHolder(view)  //display number+text
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        //calculate the values to be displayed on history_listview.xml inside show_dice_history.xml
        val sum = listOfRolls[position]
        val each = listOfDices[position]
        val dice_highest = Collections.max(each)
        val dice_lowest = Collections.min(each)
        val dice_average = sum/each.size
        viewHolder.diceId?.text = Integer.toString(position + 1)
        viewHolder.diceSum?.text = sum.toString()
        viewHolder.diceEach?.text = each.toString()
        viewHolder.diceHighest?.text = dice_highest.toString()
        viewHolder.diceLowest?.text = dice_lowest.toString()
        viewHolder.diceAverage?.text = dice_average.toString()

        return view as View
    }

    override fun getItem(position: Int): Any {
        return listOfRolls[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listOfRolls.size
    }

}