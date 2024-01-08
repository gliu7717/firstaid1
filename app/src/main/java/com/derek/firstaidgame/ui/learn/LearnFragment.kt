package com.derek.firstaidgame.ui.learn

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.derek.firstaidgame.R
import com.derek.firstaidgame.databinding.FragmentLearnBinding
import java.util.Locale

class LearnFragment : Fragment() {

    private var _binding: FragmentLearnBinding? = null
    private lateinit var mSpeech: TextToSpeech
    private lateinit var learnViewModel:LearnViewModel
    private var currentSelectedItem : LearnItem? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        learnViewModel =
            ViewModelProvider(this).get(LearnViewModel::class.java)

        _binding = FragmentLearnBinding.inflate(inflater, container, false)
        val root: View = binding.root

        learnViewModel.initLearnItems(binding)

        initSpeech()
        binding.ivSpeak.setOnClickListener {
            var str:String = binding.tvQuestion.text.toString()
            if(str.isNotEmpty()){
                mSpeech.speak(str, TextToSpeech.QUEUE_FLUSH,null,null)
            }
        }
        learnViewModel.question.observe(viewLifecycleOwner){
            binding.tvQuestion.text = it
        }

        binding.tvItem1.setOnClickListener {
            learnViewModel.selectLearnItem(binding.tvItem1)
        }
        binding.tvItem2.setOnClickListener {
            learnViewModel.selectLearnItem(binding.tvItem2)
        }
        binding.tvItem3.setOnClickListener {
            learnViewModel.selectLearnItem(binding.tvItem3)
        }
        binding.tvItem4.setOnClickListener {
            learnViewModel.selectLearnItem(binding.tvItem4)
        }

        learnViewModel.learnItems.observe(viewLifecycleOwner){
            binding.btnConfirm.isEnabled = false
            currentSelectedItem = null
            for(item in it){
                if(item.selected) {
                    item.tvItem.background =
                        ResourcesCompat.getDrawable(resources, R.drawable.round_green_shape, null)
                    binding.btnConfirm.isEnabled = true
                    currentSelectedItem = item
                    var str:String = item.tvItem.text.toString()
                    mSpeech.speak(str, TextToSpeech.QUEUE_FLUSH,null,null)
                }
                else
                    item.tvItem.background = ResourcesCompat.getDrawable(resources, R.drawable.round_light_grey_shape,null)
            }
        }

        binding.btnConfirm.setOnClickListener {
            confirm()
        }
        return root
    }

    private fun confirm() {
        if(currentSelectedItem!=null){
            if(learnViewModel.judge(binding.tvQuestion.text.toString(),
                    currentSelectedItem!!.tvItem.text.toString() )){
                currentSelectedItem!!.tvItemNum.text = "\u2714"
                currentSelectedItem!!.tvItemNum.background = ResourcesCompat.getDrawable(resources, R.drawable.round_green_shape,null)
            }
            else{
                currentSelectedItem!!.tvItemNum.text = "\u2718"
                currentSelectedItem!!.tvItemNum.background = ResourcesCompat.getDrawable(resources, R.drawable.round_red_shape,null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initSpeech() {
        mSpeech = TextToSpeech(context, TextToSpeech.OnInitListener {
            if (it == TextToSpeech.SUCCESS) {
                val i = mSpeech.setLanguage(Locale.ENGLISH)
                if (i == TextToSpeech.LANG_MISSING_DATA || i == TextToSpeech.LANG_NOT_SUPPORTED) {
                    mSpeech.setSpeechRate(1.0f)
                }
            }
        })
    }

}