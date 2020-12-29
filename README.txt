Grade: 97/100
-3 code is not organized in folders
In this file you should include:

Any information you think we should know about your submission
* Is there anything that doesn't work? Why?
No.
* Is there anything that you did that you feel might be unclear? Explain it here.
No.

A description of the creative portion of the assignment
* Describe your feature
Display the values for each dice in each roll, and display the max/min/average dice value in each roll.

* Why did you choose this feature?
Currently we only have the sum of dice values in each roll, the user might want to know the results for each dice in each roll.
Maximum, minimum and average value of the dices in each roll are clear statistics to get a sense of each roll.

* How did you implement it?
(1) A new layout show_dice_history.xml, which contains a new listview history_listview.xml to display the history.
Inside the listview, I create two LinearLayout of TextViews, one to display the headers, one to display the values.
I also create a new HistoryListAdapter to update the value listview and display it.
(2) A new button "Show Dice Results" on the RollActivity, click will lead to a new DiceResult activity, that activity will lead to a new HistoryFragment. 
Inside the fragment, listview is updated.
