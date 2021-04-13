package com.mads2202.beatboxmvvm

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


abstract class SingleFragmentActivity:AppCompatActivity() {
    protected abstract fun createFragment(): Fragment?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        val manager = supportFragmentManager
        var fragment = manager.findFragmentById(com.mads2202.beatboxmvvm.R.id.fragments_container)
        if (fragment == null) {
            fragment = createFragment()
            manager.beginTransaction()
                .add(com.mads2202.beatboxmvvm.R.id.fragments_container, fragment!!)
                .commit()
        }
    }
    protected open fun getLayoutResId(): Int {
        return com.mads2202.beatboxmvvm.R.layout.activity_fragment
    }

}