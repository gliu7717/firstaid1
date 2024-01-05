package com.derek.firstaidgame.ui.learn

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derek.firstaidgame.R
import com.derek.firstaidgame.databinding.FragmentLearnBinding

class LearnViewModel : ViewModel() {
    val learnItems = MutableLiveData<List<LearnItem>>()
    private var listItems = mutableListOf<LearnItem>()

    fun initLearnItems(binding: FragmentLearnBinding)
    {
        var item1 = LearnItem(binding.tvItem1, binding.tvOne, false, R.drawable.round_light_grey_shape)
        listItems.add(item1)
        var item2 = LearnItem(binding.tvItem2, binding.tvTwo, false, R.drawable.round_light_grey_shape)
        listItems.add(item2)
        var item3 = LearnItem(binding.tvItem3, binding.tvThree, false, R.drawable.round_light_grey_shape)
        listItems.add(item3)
        var item4 = LearnItem(binding.tvItem4, binding.tvFour, false, R.drawable.round_light_grey_shape)
        listItems.add(item4)
        learnItems.postValue(listItems)
    }

    fun selectLearnItem(tvItem: TextView) {
        for(item in listItems){
            if(item.tvItem == tvItem)
                item.selected = !item.selected
            else
                item.selected = false
        }
        learnItems.postValue(listItems)
    }

    fun judge(question:String, answer:String) : Boolean
    {
        return (Dictionary.dict.get(question) == answer)
    }
}