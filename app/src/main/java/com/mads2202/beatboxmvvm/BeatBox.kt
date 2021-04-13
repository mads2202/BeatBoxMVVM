package com.mads2202.beatboxmvvm

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.IOException

open class BeatBox(val context: Context) {
    val assetsManager = context.assets
    val sounds = mutableListOf<Sound>()
    var mSoundPool: SoundPool
    var rate=1.0f

    companion object {
        const val TAG = "BeatBox"
        const val SOUND_FOLDER = "sampleSounds"
        const val MAX_SOUNDS = 5
    }

    init {
        mSoundPool = SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0)
        loadSounds()
    }


    private fun loadSounds() {
        try {
            val soundNames = assetsManager.list(SOUND_FOLDER)
            Log.i(TAG, "Found  ${soundNames?.size} sounds")
            if (soundNames != null) {
                for (item in soundNames) {
                    var path = "$SOUND_FOLDER/$item"
                    var sound = Sound(path)
                    load(sound)
                    sounds.add(sound)
                }

            }
        } catch (e: IOException) {
            Log.e(TAG, "loadSounds $e")
        }
    }

    fun load(sound: Sound) {
        sound.mSoundId = mSoundPool.load(assetsManager.openFd(sound.path), 1)
    }
    fun play(sound:Sound){
        sound.mSoundId?.let {
            mSoundPool.play(it, 1.0f, 1.0f, 1, 0, rate)
        }
    }
    fun release(){
        mSoundPool.release()
    }
}