package com.example.breathapplicationdungle;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.media.effect.Effect;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Inhale extends AppCompatActivity {
    String[] textToShow = {"inhale", "hold\n......", "exhale","hold\n......"};
    int[] timestamps = {4000,4000,4000,4000};
    
    int[] square = {4000,4000,4000,4000};
    int[] uijjiyi = {7000,0,7000,0};
    int[] pranayama = {7000,4000,8000,4000};
    int[] custom = {5000,4000,5000,3000};

    TextView txt;
    View Inner_Circle;
    ImageView Outer_Circle;
    SoundPool soundPool;
    AnimationDrawable animationDrawable;

    int index =0;
    int Inhale_Sound, Exhale_Sound; // for SoundPool
    public Boolean TextFlag = true; // Enable/ disable text
    public Boolean LightFlag = true;
    public Boolean soundEffect = true; // Enable/ disable sounds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////////////////////////////// SoundPool //////////////////////////////////////////////
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder().build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6).setAudioAttributes(audioAttributes).build();
        } else{
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        }

        Inhale_Sound = soundPool.load(this, R.raw.inhale_breathing,1);
        Exhale_Sound = soundPool.load(this, R.raw.exhale_breathing,1);
        ///////////////////////////////////////////////////////////////////////////////////////////


        Inner_Circle = (View) findViewById(R.id.InnerCircle); // That show text
        Outer_Circle = (ImageView) findViewById(R.id.OuterCircle); // For the animation
        txt = (TextView) findViewById(R.id.EnhaleExhale);



        //DisplayText(TextFlag);
        //LightSync(LightFlag);

        ////////////////////////////////////////// Breathing Patterns ////////////////////////////
        //SquarePattern(square, TextFlag,soundEffect);
        //UijjiyiPattern(uijjiyi,TextFlag,soundEffect);
        //PranayamaPattern(pranayama,TextFlag,soundEffect);
        Custom(custom, TextFlag, soundEffect);
        //////////////////////////////////////////////////////////////////////////////////////////


        /*animationDrawable = (AnimationDrawable) Outer_Circle.getBackground();
        animationDrawable.setEnterFadeDuration(pranayama[0]);
        animationDrawable.setExitFadeDuration(pranayama[2]);
        animationDrawable.start();*/
    }

    private void Custom(int[] custom, Boolean textFlag, Boolean soundEffect) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    while (!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { //// SHOW TEXT HERE

                                animationDrawable = (AnimationDrawable) Outer_Circle.getBackground();

                                animationDrawable.selectDrawable(R.drawable.inhale_exhale_gradient);
                                animationDrawable.setEnterFadeDuration(custom[0]);
                               // animationDrawable.setExitFadeDuration(custom[0]/2);

                                animationDrawable.selectDrawable(R.drawable.hold_inhale_gradient);
                                //animationDrawable.setEnterFadeDuration(custom[1]/2);
                                animationDrawable.setEnterFadeDuration(custom[1]);

                                animationDrawable.selectDrawable(R.drawable.inhale_exhale_gradient);
                                animationDrawable.setExitFadeDuration(custom[2]);
                               // animationDrawable.setExitFadeDuration(custom[2]/2);

                                animationDrawable.selectDrawable(R.drawable.hold_exhale_gradient);
                                //animationDrawable.setEnterFadeDuration(custom[3]/2);
                                animationDrawable.setExitFadeDuration(custom[3]);
                                /*animationDrawable.getFrame(R.drawable.inhale_exhale_gradient);
                                animationDrawable.getDuration(custom[0]);
                                animationDrawable.getFrame(R.drawable.hold_inhale_gradient);
                                animationDrawable.getDuration(custom[1]);
                                animationDrawable.getFrame(R.drawable.inhale_exhale_gradient);
                                animationDrawable.getDuration(custom[2]);
                                animationDrawable.getFrame(R.drawable.hold_exhale_gradient);
                                animationDrawable.getDuration(custom[3]);*/

                                //animationDrawable.setEnterFadeDuration(custom[0]);
                                //animationDrawable.setExitFadeDuration(custom[2]);
                                animationDrawable.start();


                                if(index == 0 && soundEffect){
                                    soundPool.play(Inhale_Sound, 1, 1, 1, 0, 1);
                                }

                                if(textFlag) {
                                    if (index == textToShow.length) {
                                        index = 0;
                                        txt.setText(textToShow[0]);
                                    } else {
                                        txt.setText(textToShow[index]);
                                        Log.d("text", "index: " + textToShow[index]);
                                    }
                                }
                                else {}
                            }
                        });

                        if(index == custom.length){
                            index = 0;
                            /*if(soundEffect) {
                                soundPool.play(Inhale_Sound, 1, 1, 0, 0, 1);
                            }*/
                            Thread.sleep(custom[index]);
                            index++;
                        }
                        else if(index == 2){
                            if(soundEffect) {
                                soundPool.play(Exhale_Sound, 1, 1, 0, 0, 1);
                            }
                            Thread.sleep(custom[index]);
                            index++;
                        }else{
                            Thread.sleep(custom[index]);
                            index++;
                        }

                    }
                }catch (Exception e){}
            }
        };
        thread.start();
    }

    private void initial() {

    }

    private void PranayamaPattern(int[] pranayama, Boolean textFlag, Boolean soundEffect) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    while (!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { //// SHOW TEXT HERE
                               // Context context;
                                //Outer_Circle.setBackground(ContextCompat.getDrawable(Inhale.this, R.drawable.pranamya_gradient));
                                Outer_Circle.setBackgroundResource(R.drawable.pranamya_gradient);
                                animationDrawable = (AnimationDrawable) Outer_Circle.getBackground();
                                animationDrawable.setEnterFadeDuration(pranayama[0]);
                                animationDrawable.setExitFadeDuration(pranayama[2]);
                                animationDrawable.start();

                                if(index == 0 && soundEffect){
                                    soundPool.play(Inhale_Sound, 1, 1, 1, 0, 1);
                                }

                                if(textFlag) {
                                    if (index == textToShow.length) {
                                        index = 0;
                                        txt.setText(textToShow[0]);
                                    } else {
                                        txt.setText(textToShow[index]);
                                        Log.d("text", "index: " + textToShow[index]);
                                    }
                                }
                                else {}
                            }
                        });

                        if(index == pranayama.length){
                            index = 0;
                            /*if(soundEffect) {
                                soundPool.play(Inhale_Sound, 1, 1, 0, 0, 1);
                            }*/
                            Thread.sleep(pranayama[index]);
                            index++;
                        }
                        else if(index == 2){
                            if(soundEffect) {
                                soundPool.play(Exhale_Sound, 1, 1, 0, 0, 1);
                            }
                            Thread.sleep(pranayama[index]);
                            index++;
                        }else{
                            Thread.sleep(pranayama[index]);
                            index++;
                        }

                    }
                }catch (Exception e){}
            }
        };
        thread.start();
    }

    private void UijjiyiPattern(int[] uijjiyi, Boolean textFlag, Boolean soundEffect) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    while (!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { //// SHOW TEXT HERE
                                Outer_Circle.setBackgroundResource(R.drawable.uijjiyi_gradient);
                                animationDrawable = (AnimationDrawable) Outer_Circle.getBackground();
                                animationDrawable.setEnterFadeDuration(uijjiyi[0]);
                                animationDrawable.setExitFadeDuration(uijjiyi[2]);
                                animationDrawable.start();

                                if(index == 0 && soundEffect){
                                    soundPool.play(Inhale_Sound, 1, 1, 0, 0, 1);
                                }

                                if(textFlag) {
                                    if (index == textToShow.length) {
                                        index = 0;
                                        txt.setText(textToShow[0]);
                                    } else {
                                        txt.setText(textToShow[index]);
                                        Log.d("text", "index: " + textToShow[index]);
                                    }
                                }
                                else{}
                            }
                        });

                        if(index == uijjiyi.length){
                            index = 0;
                            /*if(soundEffect) {
                                soundPool.play(Inhale_Sound, 1, 1, 0, 0, 1);
                            }*/
                            Thread.sleep(uijjiyi[index]);
                            index++;
                        }else if(index == 2){
                            if(soundEffect) {
                                soundPool.play(Exhale_Sound, 1, 1, 0, 0, 1);
                            }
                            Thread.sleep(uijjiyi[index]);
                            index++;
                        }
                        else{
                            Thread.sleep(uijjiyi[index]);
                            index++;
                        }

                    }
                }catch (Exception e){}
            }
        };
        thread.start();
    }

    private void SquarePattern(int[] square, Boolean textFlag, Boolean soundEffect) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    while (!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { //// SHOW TEXT HERE
                                animationDrawable = (AnimationDrawable) Outer_Circle.getBackground();
                                animationDrawable.setEnterFadeDuration(square[0]);
                                animationDrawable.setExitFadeDuration(square[2]);
                                animationDrawable.start();

                                /*if(firstTime == 1){
                                    //firstTime = 0;
                                   if(soundEffect) {
                                    soundPool.play(Inhale_Sound, 1, 1, 1, 0, 1);
                                   }
                                }*/
                                if(index == 0 && soundEffect){
                                    soundPool.play(Inhale_Sound, 1, 1, 1, 0, 1);
                                }
                                
                                if (textFlag) {
                                    if (index == textToShow.length) {
                                        index = 0;
                                        txt.setText(textToShow[0]);
                                    } else {
                                        txt.setText(textToShow[index]);
                                        Log.d("text", "index: " + textToShow[index]);
                                    }
                                }
                                else{}
                            }
                        });

                        if(index == square.length){
                            index = 0;
                            /*if(soundEffect) {
                                soundPool.play(Inhale_Sound, 1, 1, 1, 0, 1);
                            } */
                            Thread.sleep(square[index]);
                            index++;
                        }else if(index == 2){
                            if(soundEffect) {
                                soundPool.play(Exhale_Sound, 1, 1, 1, 0, 1);
                            }
                            Thread.sleep(square[index]);
                            index++;
                        }
                        else{
                            Thread.sleep(square[index]);
                            index++;
                        }

                    }
                }catch (Exception e){}
            }
        };
        thread.start();
    }



    private void LightSync(Boolean Light) {
    }


    ////////////////////////////////////////////////// ENABLE TEXT SWITCH
    /*private void DisplayText(Boolean ShowText) {
        ///////////// IF IT IS ENABLED
        if(ShowText){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                        while (!isInterrupted()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() { //// SHOW TEXT HERE
                                    if(index == textToShow.length){
                                        index=0;
                                        txt.setText(textToShow[0]);
                                    }else{
                                        txt.setText(textToShow[index]);
                                        Log.d("text","index: "+textToShow[index]);
                                    }
                                }
                            });

                            if(index == timestamps.length){
                                index = 0;
                                Thread.sleep(timestamps[index]);
                                index++;
                            }else{
                                Thread.sleep(timestamps[index]);
                                index++;
                            }

                        }
                    }catch (Exception e){}
                }
            };
            thread.start();
        }
        //////////// if switch button OFF
        else if (!ShowText){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    super.run();
                    try{
                        while (!isInterrupted()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() { /// Not showing text
                                }
                            });

                            if(index == timestamps.length){
                                index = 0;
                                Thread.sleep(timestamps[index]);
                                index++;
                            }else{
                                Thread.sleep(timestamps[index]);
                                index++;
                            }

                        }
                    }catch (Exception e){}
                }
            };
            thread.start();
        }
    } */
}