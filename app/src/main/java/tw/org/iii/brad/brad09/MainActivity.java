package tw.org.iii.brad.brad09;
//資料儲存機制
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("config",MODE_PRIVATE);//自己命名,其實是存成檔案(檔名為此處命名)裡面存資料
        editor = sp.edit();
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
        Toast.makeText(this,username +":"+ stage +":"+ sound,Toast.LENGTH_SHORT).show();
    }

    public void test3(View view) {
        try {
//            Log.v("brad","test3");
            FileOutputStream fout = openFileOutput("brad.txt",MODE_PRIVATE);
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
            Toast.makeText(this,sb,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.v("brad", e.toString());
        }
    }
}
