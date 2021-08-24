package com.example.fungame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.home1:
              break;
            case R.id.help1:
                Toast.makeText(this,"Help",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting1:
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private String [] question={"Bug","Uninterested","Problem","Does'not Work"};
    private boolean [] answer={false,false,false,false};
    private AlertDialog.Builder alert;
    private String problem="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void normaloyunclick(View v) {
        Intent intent = new Intent(MainActivity.this, NormalOyun.class);
        finish();
        startActivity(intent);
    }

    public void quitclick(View v) {
        alert =new AlertDialog.Builder(this);
        alert.setCancelable(false);
        alert.setTitle("Why Do You Want To Exit?");
        DialogInterface.OnMultiChoiceClickListener listener=new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean b) {
                if(b){
                    problem=problem+question[i];
                }
            }
        };
        alert.setMultiChoiceItems(question,answer,listener);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Thanks for FeedBack \n"+problem, Toast.LENGTH_SHORT).show();
                problem="";
            }
        });

       alert.show();

    }

    public void secondclick(View v) {
        Toast.makeText(getApplicationContext(), "Tam Hazir Deyil", Toast.LENGTH_SHORT).show();
    }
}