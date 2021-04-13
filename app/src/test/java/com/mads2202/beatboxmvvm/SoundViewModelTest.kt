package com.mads2202.beatboxmvvm

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


internal class SoundViewModelTest {
    lateinit var beatBox: BeatBox
    lateinit var sound: Sound
    lateinit var soundViewModel: SoundViewModel


    @Before
    fun setUp() {
        beatBox = Mockito.mock(BeatBox::class.java)
        sound = Sound("path")
        soundViewModel= SoundViewModel(beatBox)
        soundViewModel.sound=sound
    }

    @Test
    fun exposesSoundNameAsTitle() {
        Assert.assertEquals(soundViewModel.getTitle(), sound.name)
    }

    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        soundViewModel.onButtonClicked()
        Mockito.verify(beatBox).play(sound)

    }
}