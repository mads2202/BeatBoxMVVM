package com.mads2202.beatboxmvvm

class Sound(var path:String) {
    var name:String?=null
    var mSoundId:Int?=null
    init{
        val components=path.split("/")
        val fileName=components[components.size-1]
        name=fileName.replace(".vaw","")
    }
}