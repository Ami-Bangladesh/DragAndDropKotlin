package com.example.draganddropkotlin

import android.support.v7.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import android.view.DragEvent
import android.view.Menu
import android.view.MenuItem
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.view.MotionEvent
import android.view.View.DragShadowBuilder
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout

class MainActivity : Activity(), OnTouchListener, OnDragListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //set ontouch listener for box views
        findViewById<View>(R.id.box_view).setOnTouchListener(this)

        //set ondrag listener for right and left parent views
        findViewById<View>(R.id.left_view).setOnDragListener(this)
        findViewById<View>(R.id.right_view).setOnDragListener(this)
    }

    override fun onDrag(v: View, event: DragEvent): Boolean {
        // TODO Auto-generated method stub
        if (event.action == DragEvent.ACTION_DROP) {
            //we want to make sure it is dropped only from left to right parent view
            val view = event.localState as View

            if (v.id == R.id.right_view) {

                val source = view.parent as ViewGroup
                source.removeView(view)

                val target = v as LinearLayout
                target.addView(view)

                val intent = Intent(this, DisplayMessageActivity::class.java)
                val message = "HARUKA :)".toString()
                intent.putExtra(EXTRA_MESSAGE, message)
                startActivity(intent)

            }
            //make view visible as we set visibility to invisible while starting drag
            view.visibility = View.VISIBLE
            /*
            Intent intent = new Intent(this, DisplayMessageActivity.class);
            String message = "HARUKA :)".toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent); */
        }
        return true
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        // TODO Auto-generated method stub
        if (event.action == MotionEvent.ACTION_DOWN) {
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDrag(null, shadowBuilder, view, 0)
            view.visibility = View.INVISIBLE
            return true
        }
        return false
    }

    companion object {

        val EXTRA_MESSAGE = "com.example.draganddropkotlin.MESSAGE"
    }

}
