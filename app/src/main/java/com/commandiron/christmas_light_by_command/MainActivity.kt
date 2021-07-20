package com.commandiron.christmas_light_by_command

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.graphics.ColorUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    private var luminance: Float? = null
    private var delay: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        luminance = 0.5f

        start_btn.setOnClickListener {

            handler.removeCallbacksAndMessages(null)

            runnableStart(red_light,0f,setDelay(),setPostDelay(red_postDelay,500), luminance!!)
            runnableStart(orange_light,31f,setDelay(),setPostDelay(orange_postDelay,1000), luminance!!)
            runnableStart(yellow_light,60f,setDelay(),setPostDelay(yellow_postDelay,1500), luminance!!)
            runnableStart(green_light,120f,setDelay(),setPostDelay(green_postDelay,2000), luminance!!)
            runnableStart(blue_light,210f,setDelay(),setPostDelay(blue_postDelay,2500), luminance!!)
            runnableStart(navy_light,240f,setDelay(),setPostDelay(navy_postDelay,3000), luminance!!)
            runnableStart(purple_light,270f,setDelay(),setPostDelay(purple_postDelay,3500), luminance!!)
        }
    }

    fun runnableStart(linearLayout: LinearLayout, colorFloat: Float, delay: Long, postDelay:Long, luminance: Float){

        var runnable = Runnable {}

        var luminance = luminance

        var status: Boolean = true //increase(true), decrease(false)

        runnable = object: Runnable{
            override fun run() {

                if(luminance >= 1.0f && status == true){
                    status = false
                }else if(luminance == 0.5f && status == false){
                    status = true
                }

                if(status == true){
                    val color = ColorUtils.HSLToColor(floatArrayOf(colorFloat, 1f, luminance))
                    linearLayout.setBackgroundColor(color)
                    luminance = luminance + 0.01f
                    handler.postDelayed(runnable, delay)
                }else if(status == false){
                    val color = ColorUtils.HSLToColor(floatArrayOf(colorFloat, 1f, luminance))
                    linearLayout.setBackgroundColor(color)
                    luminance = luminance - 0.01f
                    handler.postDelayed(runnable, delay)
                }
            }
        }
        handler.postDelayed(runnable,postDelay)
    }

    fun setDelay(): Long{
        if (delay_et.text.toString() != ""){
            delay = delay_et.text.toString().toLong()
        }else{
            delay = 5
        }
        return delay!!
    }

    fun setPostDelay(editText: EditText, naturalPostDelay: Long): Long{
        var postDelay: Long
        if (editText.text.toString() != ""){
            postDelay = editText.text.toString().toLong()
        }else{
            postDelay = naturalPostDelay
        }
        return postDelay
    }

}