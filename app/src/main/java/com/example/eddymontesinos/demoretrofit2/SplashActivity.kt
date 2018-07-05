package com.example.eddymontesinos.demoretrofit2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val anima : Animation
        val anima2 : Animation

        anima = AnimationUtils.loadAnimation(this, R.anim.ani_arriba)
        anima2 = AnimationUtils.loadAnimation(this,R.anim.ani_abajo)
        once.animation=anima
        doce.animation=anima2

        llamarSplash()

    }

    fun llamarSplash (){
        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep((3*1000).toLong())
                    val i = Intent(baseContext, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {


                    e.printStackTrace()
                }

            }
        }
        background.start()
    }
}
