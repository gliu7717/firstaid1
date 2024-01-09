package com.derek.firstaidgame.ui.learn

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.derek.firstaidgame.R
import com.derek.firstaidgame.databinding.FragmentLearnBinding
import java.util.Collections

class LearnViewModel : ViewModel() {
    val learnItems = MutableLiveData<List<LearnItem>>()
    val question = MutableLiveData<String>()
    private var listItems = mutableListOf<LearnItem>()

    var samples:MutableMap<String, List<String>> = mutableMapOf()
    var currentItemIndex = 0

    fun initSamples(count :Int)
    {
        var sampleCount = Math.min(count, Dictionary.dict.size)
        var list: List<String> = ArrayList<String>(Dictionary.dict.keys)
        Collections.shuffle(list)
        for(i in 0 until sampleCount){
            var answerItems: List<String> = listOf()
            answerItems += Dictionary.dict[list[i]].toString()
            Dictionary.dict.remove(list[i])
            var items: List<String> = ArrayList<String>(Dictionary.dict.values)
            Collections.shuffle(items)
            for(j in 0 until 3)
                answerItems += items[j]
            Dictionary.dict.put(list[i], answerItems[0])
            Collections.shuffle(answerItems)
            samples.put(list[i], answerItems)
        }
    }

    fun updateCurrentItem()
    {
        if(currentItemIndex < samples.size)
        {
            var currentQuestion = ArrayList(samples.keys)[currentItemIndex]
            question.postValue(currentQuestion)
            for(i in 0 until listItems.size){
                listItems[i].tvItem.text = samples.get(currentQuestion)?.get(i)
                listItems[i].selected = false
            }
            learnItems.postValue(listItems)
        }
    }

    fun initLearnItems(binding: FragmentLearnBinding)
    {
        initSamples(20)
        var item1 = LearnItem(1, binding.tvItem1, binding.tvOne, false, R.drawable.round_light_grey_shape)
        listItems.add(item1)
        var item2 = LearnItem(2, binding.tvItem2, binding.tvTwo, false, R.drawable.round_light_grey_shape)
        listItems.add(item2)
        var item3 = LearnItem(3,binding.tvItem3, binding.tvThree, false, R.drawable.round_light_grey_shape)
        listItems.add(item3)
        var item4 = LearnItem(4,binding.tvItem4, binding.tvFour, false, R.drawable.round_light_grey_shape)
        listItems.add(item4)
        //learnItems.postValue(listItems)
        updateCurrentItem()
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

    fun nextItem() {
        if( currentItemIndex < samples.size - 1) {
            currentItemIndex++
            updateCurrentItem()
        }
    }
}