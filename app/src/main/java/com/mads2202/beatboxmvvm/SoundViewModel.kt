package com.mads2202.beatboxmvvm

import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel(val beatBox: BeatBox) : BaseObservable() {

    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }
    @Bindable
    fun getTitle(): String? {
        return sound!!.name
    }

    fun onButtonClicked() {
        sound?.let { beatBox.play(it) }
    }

}