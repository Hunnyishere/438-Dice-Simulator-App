package com.example.cse438.cse438_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.display.*
import java.util.*
import kotlin.collections.ArrayList

class RollActivity : AppCompatActivity() {

    public var listOfRolls = ArrayList<Int>()
    public var listOfDices = ArrayList<ArrayList<Int>>()
    public var current_dices = ArrayList<Int>()
    public var num_of_dice: Int = 0
    public var max_dice_value: Int = 0
    public var current_roll: Int = 0
    public var highest: Int = 0
    public var lowest: Int = 0
    public var average: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display)
    }

    override fun onStart() {
        super.onStart()

        //get data from MainActivity
        var bundle: Bundle? = intent.extras
        listOfRolls = bundle!!.getIntegerArrayList("listOfRolls")
        listOfDices = bundle!!.getSerializable("listOfDices") as ArrayList<ArrayList<Int>>
        num_of_dice = bundle!!.getInt("num_of_dice")
        max_dice_value = bundle!!.getInt("max_dice_value")

        //random roll dices
        var temp_dice: Int = 0
        for (i in 1 until (num_of_dice + 1)) { // [1,num_of_dice+1)
            temp_dice = (1..max_dice_value).random()
            current_dices.add(temp_dice)
            current_roll += temp_dice
        }
        //update roll value display
        value_num.text = current_roll.toString()
        //update listOfDices arraylist
        listOfDices.add(current_dices)


        //calculate highest,lowest,average
        //only value > previous highest shows as green, only value < previous lowest shows as red, all others in black
        if (listOfRolls.size == 0) {
            highest = current_roll
            lowest = current_roll
            average = current_roll
            value_num.setTextColor(android.graphics.Color.GREEN)
            listOfRolls.add(current_roll)
        } else {
            highest = Collections.max(listOfRolls)
            lowest = Collections.min(listOfRolls)
            if (current_roll > highest) {
                highest = current_roll
                value_num.setTextColor(android.graphics.Color.GREEN)
            } else if (current_roll < lowest) {
                lowest = current_roll
                value_num.setTextColor(android.graphics.Color.RED)
            } else {
                value_num.setTextColor(android.graphics.Color.BLACK)
            }
            listOfRolls.add(current_roll)
            var sum: Int = 0
            for (roll in listOfRolls) {
                sum += roll
            }
            average = sum / listOfRolls.size
            current_roll = 0
        }

        //set on click listener for buttons
        //see results for each roll (sum of dices in that roll)
        see_roll_result_button.setOnClickListener {
            val roll_result_intent = Intent(this, RollResultActivity::class.java)
            var roll_result_bundle = Bundle()
            roll_result_bundle.putInt("highest", highest)
            roll_result_bundle.putInt("lowest", lowest)
            roll_result_bundle.putInt("average", average)
            roll_result_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
            roll_result_bundle.putSerializable("listOfDices", listOfDices)
            roll_result_intent.putExtras(roll_result_bundle)
            startActivity(roll_result_intent)
        }

        //see results for each dice in each roll
        see_dice_result_button.setOnClickListener {
            val dice_result_intent = Intent(this, DiceResultActivity::class.java)
            var dice_result_bundle = Bundle()
            dice_result_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
            dice_result_bundle.putSerializable("listOfDices", listOfDices)
            dice_result_intent.putExtras(dice_result_bundle)
            startActivity(dice_result_intent)
        }

        //click the "reroll" button, re-load the RollActivity
        reroll_button.setOnClickListener {
            val reroll_intent = Intent(this, RollActivity::class.java)
            var reroll_bundle = Bundle()
            reroll_bundle.putInt("num_of_dice", num_of_dice)
            reroll_bundle.putInt("max_dice_value", max_dice_value)
            reroll_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
            reroll_bundle.putSerializable("listOfDices", listOfDices)
            reroll_intent.putExtras(reroll_bundle)
            startActivity(reroll_intent)
            }

            //click the "home" button, re-load the MainActivity
            home_button.setOnClickListener {
                val home_intent = Intent(this, MainActivity::class.java)
                var home_bundle = Bundle()
                home_bundle.putIntegerArrayList("listOfRolls", listOfRolls)
                home_bundle.putSerializable("listOfDices", listOfDices)
                home_intent.putExtras(home_bundle)
                startActivity(home_intent)
            }

        }
    }