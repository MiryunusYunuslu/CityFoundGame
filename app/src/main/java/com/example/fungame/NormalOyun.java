package com.example.fungame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class NormalOyun extends AppCompatActivity {


    private String[] city = {"Bakı", "Gəncə", "Ağdam", "Cəlilabad", "Xızı", "Şəki", "Şəmkir", "Ağstafa", "Masallı", "Sumqayıt", "Lənkəran","Bərdə","Ağdərə",
            "Ağdaş","Quba","Qusar","Balakən","Biləsuvar","Xocalı","Xocavənd","Hacıqabul","Naxçıvan","Salyan","Şirvan","Qax","Qubadlı","Saatlı","Samux","Şuşa",
            "Zaqatala","Zərdab","Ucar","Yardımlı","Tovuz","Tərtər"
    };
    private Random random, random1;
    private Switch music;
    private MediaPlayer media;
    private int randnumber, randomcahrs, count,givenletters,point=10,toplamxal=0,checkletter=0;
    private String comecity, space = "";
    private TextView text1, text2,alert;
    private ArrayList<Character> gelenherfler;
    private char[] some;
    private EditText tahmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_oyun);
        Toast.makeText(getApplicationContext(),"HER DEFE HERF ALANDA 1 BALINIZ GEDECEK",Toast.LENGTH_LONG).show();
        text1 = findViewById(R.id.textView2);
        text2 = findViewById(R.id.textView3);
        alert=findViewById(R.id.point);
        tahmin=findViewById(R.id.editTextTextPersonName);
        music=findViewById(R.id.switch2);
        gelenherfler = new ArrayList<>();
        random = new Random();
        random1 = new Random();
        media=MediaPlayer.create(getApplicationContext(),R.raw.m2);
        sozgetirme();
        if(gelenherfler.size()>=5 && gelenherfler.size()<=8){
            givenletters=2;
        }
        else if(gelenherfler.size()>8 && gelenherfler.size()<=10){
            givenletters=3;
        }
        else if(gelenherfler.size()==4){
            givenletters=1;
        }
        for(int i=0;i<givenletters;i++){
            herfalma2();
        }
        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    media.start();
                    music.setText("Musiqini Saxla");
                }
                else{
                    media.pause();
                    music.setText("Musiqini Dinle");
                }
            }
        });

    }
    public void sozgetirme(){
        gelenherfler.clear();
        randnumber = random.nextInt(city.length);
        comecity = city[randnumber];
        text1.setText(comecity.length() + " Herfli");
        for (int i = 0; i < comecity.length(); i++) {
            if (i < comecity.length() - 1) {
                space += "_ ";
            } else {
                space += "_";
            }
        }
        text2.setText(space);

        for (char c:comecity.toCharArray()) {
            gelenherfler.add(c);
        }
        if(comecity.length()>=6){
            count=(comecity.length()/2)-1;
        }
        else if(comecity.length()>3){
            count=1;
        }
        else{
            count=2;
        }
    }
    public void getchar(View v) {
        if (gelenherfler.size() >0 && count>0) {

            checkletter++;
            randomcahrs = random.nextInt(gelenherfler.size());
            char[] elaveherfler = comecity.toCharArray();

            String[] txtherfler = text2.getText().toString().split(" ");
            System.out.println(txtherfler.length);
            for (int i = 0; i < comecity.length(); i++) {
                if (txtherfler[i].equals("_") && elaveherfler[i] == gelenherfler.get(randomcahrs)) {
                    txtherfler[i] = String.valueOf(gelenherfler.get(randomcahrs));
                    space="";
                    for (int j = 0; j < comecity.length(); j++) {
                        if (j == i) {
                        space+= txtherfler[j]+" ";
                        }
                        else if(j<comecity.length()-1){
                            space+=txtherfler[j]+" ";
                        }
                        else{
                            space+=txtherfler[j];
                        }

                    }
                   break;

                }

            }
            text2.setText(space);
            gelenherfler.remove(randomcahrs);
            count--;
        }
        else{
            Toast.makeText(getApplicationContext(),"Hərf Almaq Olmaz!!",Toast.LENGTH_SHORT).show();
        }
    }
    public void predict(View v){
     String check =tahmin.getText().toString();
     if(!TextUtils.isEmpty(check)){
      if(check.equals(comecity))  {
          Toast.makeText(getApplicationContext(),"Tebrikler Bildiniz",Toast.LENGTH_LONG).show();
          text2.setText("");
          space="";
          toplamxal+=point-checkletter;
          alert.setText("Toplam Xal:"+(toplamxal));
          checkletter=0;
          sozgetirme();
          if(gelenherfler.size()>5 && gelenherfler.size()<=8){
              givenletters=2;
          }
          else if(gelenherfler.size()>8 && gelenherfler.size()<=10){
              givenletters=3;
          }
          else if(gelenherfler.size()<=5){
              givenletters=2;
          }
          for(int i=0;i<givenletters;i++){
              herfalma2();
          }
      }
      else{
          Toast.makeText(getApplicationContext(),"Yanlis",Toast.LENGTH_SHORT).show();
      }
     }
     else{
         Toast.makeText(getApplicationContext(),"Texminde Bulunun!!!",Toast.LENGTH_SHORT).show();
     }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 =new Intent(NormalOyun.this,MainActivity.class);
        finish();
        startActivity(intent1);
    }
    public void herfalma2(){
        if (gelenherfler.size() >0) {
            randomcahrs = random.nextInt(gelenherfler.size());
            char[] elaveherfler = comecity.toCharArray();

            String[] txtherfler = text2.getText().toString().split(" ");
            for (int i = 0; i < comecity.length(); i++) {
                if (txtherfler[i].equals("_") && elaveherfler[i] == gelenherfler.get(randomcahrs)) {
                    txtherfler[i] = String.valueOf(gelenherfler.get(randomcahrs));
                    space="";
                    for (int j = 0; j < comecity.length(); j++) {
                        if (j == i) {
                            space+= txtherfler[j]+" ";
                        }
                        else if(j<comecity.length()-1){
                            space+=txtherfler[j]+" ";
                        }
                        else{
                            space+=txtherfler[j];
                        }

                    }

                    break;

                }

            }
            text2.setText(space);
            gelenherfler.remove(randomcahrs);
        }
        else{
          Toast.makeText(getApplicationContext(),"Soz Almaq Olmadi!!",Toast.LENGTH_SHORT);
        }

    }
}