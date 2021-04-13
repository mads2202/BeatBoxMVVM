package com.mads2202.beatboxmvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSeekBar
import android.widget.SeekBar
import androidx.appcompat.view.menu.MenuView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mads2202.beatboxmvvm.databinding.FragmetBeatBoxBinding
import com.mads2202.beatboxmvvm.databinding.ListItemSoundBinding

class BeatBoxFragment : Fragment() {
    lateinit var beatBox: BeatBox
    lateinit var mBinding: FragmetBeatBoxBinding
    lateinit var seekBar: SeekBar

    companion object {
        fun newInstance(): BeatBoxFragment {
            return BeatBoxFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance=true
        beatBox = BeatBox(activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragmet_beat_box, container, false)
        mBinding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        mBinding.recyclerView.adapter = SoundAdapter(beatBox.sounds)
        seekBar=mBinding.seekBar
        seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var div=(progress.toFloat()/100)
                if(progress<50 ){
                    beatBox.rate=0.5f+div
                }else if(progress>50)
                beatBox.rate=1.0f+div

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })


        return mBinding.root
    }

    inner class SoundHolder(item: ListItemSoundBinding) : RecyclerView.ViewHolder(item.root) {
        var binding: ListItemSoundBinding

        init {
            binding = item
            binding.viewModel = SoundViewModel(beatBox)
        }

        fun bind(sound: Sound) {
            binding.viewModel!!.sound = sound
            binding.executePendingBindings()

        }

    }

    inner class SoundAdapter(val sounds: List<Sound>) : RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val layoutInflater = LayoutInflater.from(activity)
            val binding: ListItemSoundBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item_sound, parent, false)

            return SoundHolder(binding)
        }


        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            holder.bind(sounds[position])

        }

        override fun getItemCount(): Int {
            return sounds.size
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }
}

