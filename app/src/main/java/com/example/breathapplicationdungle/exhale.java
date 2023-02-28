package com.example.breathapplicationdungle;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class exhale extends AppCompatActivity{

    View Outer_Circle;
    private volatile int duration;//its volatile because another thread will update its value
    private int currentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhale);

        Outer_Circle = (View) findViewById(R.id.view3);

        /*@Override
        public void run() {
            int n = 4;
            currentFrame++;
            if (currentFrame >= n) {
                currentFrame = 0;
            }
            selectDrawable(currentFrame);
            scheduleSelf(this, SystemClock.uptimeMillis() + duration);
        }

        public void setDuration(int duration) {
            this.duration = duration;
            //we have to do the following or the next frame will be displayed after the old duration
            unscheduleSelf(this);
            selectDrawable(currentFrame);
            scheduleSelf(this, SystemClock.uptimeMillis()+duration);
        }*/
        //Outer_Circle.animate().d

        /*AnimationDrawable animation = new AnimationDrawable();
        animation.setOneShot(true);

        Resources res = this.getResources();


        animation.start();*/


        /*Animation firstSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.anim.inhale);
        firstSlideLeft.setDuration(3000);
        Outer_Circle.startAnimation(firstSlideLeft);

        Animation SecondSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.anim.hold);
        SecondSlideLeft.setDuration(4000);
        Outer_Circle.startAnimation(SecondSlideLeft);

        Animation ThirdSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.anim.exhale);
        ThirdSlideLeft.setDuration(5000);
        Outer_Circle.startAnimation(ThirdSlideLeft);

        Animation FourthSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.anim.hold_exhale);
        FourthSlideLeft.setDuration(2000);
        Outer_Circle.startAnimation(FourthSlideLeft);*/


        /*@SuppressLint("ResourceType") Animation firstSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.drawable.inhale_exhale_gradient);
        firstSlideLeft.setDuration(3000);
        Outer_Circle.startAnimation(firstSlideLeft);

        @SuppressLint("ResourceType") Animation SecondSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.drawable.hold_inhale_gradient);
        SecondSlideLeft.setDuration(4000);
        Outer_Circle.startAnimation(SecondSlideLeft);

        @SuppressLint("ResourceType") Animation ThirdSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.drawable.inhale_exhale_gradient);
        ThirdSlideLeft.setDuration(5000);
        Outer_Circle.startAnimation(ThirdSlideLeft);

        @SuppressLint("ResourceType") Animation FourthSlideLeft = AnimationUtils.loadAnimation(exhale.this, R.drawable.hold_exhale_gradient);
        FourthSlideLeft.setDuration(2000);
        Outer_Circle.startAnimation(FourthSlideLeft);*/
    }
}