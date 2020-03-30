package tw.org.iii.brad.brad09;
//資料儲存機制
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;    //SP的內部類別
    private Button test5, test6; //所有看得到的東東都是View,但宣告為何,可用的method會不同,視情況宣告

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("config",MODE_PRIVATE);//自己命名,其實是存成檔案(檔名為此處命名)裡面存資料
        editor = sp.edit(); //由此偏好設定定義的內部類別(編輯器)才能編輯,故是從SP本身身上去取得
        test5 = findViewById(R.id.test5);
        test6 = findViewById(R.id.test6);

    }

    public void test1(View view) {
        editor.putInt("stage",(int)(Math.random()*100));
        editor.putBoolean("sound",false);
        editor.putString("username","Brad");
        editor.commit();
    }

    public void test2(View view) {
        boolean sound = sp.getBoolean("sound",true);
        int stage = sp.getInt("stage",1);
        String username = sp.getString("username","nobody");
        String amIRight = sp.getString("amIRight","HELLYEAH!"); //沒有put進資料中
        Toast.makeText(this,username +":"+ stage +":"+ sound + ":" + amIRight,Toast.LENGTH_SHORT).show();
    }

    public void test3(View view) {
        try {
//            Log.v("brad","test3");
            FileOutputStream fout = openFileOutput("brad.txt",MODE_APPEND);
            fout.write("Hello, World.\n1234567\nabcdggg".getBytes());
            fout.flush();
            Toast.makeText(this,"Save OK",Toast.LENGTH_SHORT).show();
            fout.close();
        } catch (Exception e) {
            Log.v("brad", e.toString());
            Toast.makeText(this,"Save fail",Toast.LENGTH_SHORT).show();
        }
    }

    public void test4(View view) {
        try {
            FileInputStream fin = openFileInput("brad.txt");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(fin));
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = reader.readLine())!=null){
                sb.append(line + "\n");
            }
            fin.close();
            Toast.makeText(this,sb,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }

    public void test56(View view) {
        Button btn = (Button)view;
        Log.v("brad", btn.getText().toString() + " first line");
        if (view == test5){
            Log.v("brad","test5");
        }else if(view == test6){
            Log.v("brad","test6");
        }

    }
}
