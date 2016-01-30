package com.twiceyuan.multicolumnpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.twiceyuan.multicolumnpicker.picker.BusinessPicker;
import com.twiceyuan.multicolumnpicker.picker.CityPicker;
import com.twiceyuan.multicolumnpicker.picker.DatePicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        initBusinessDatabase();
    }

    /**
     * Test Pick a city
     */
    public void test(View view) {
        new CityPicker()
                .setOnSelected((city3, city21) -> action(city3.fullName + "/" + city21.fullName))
                .show(this);
    }

    /**
     * Test Pick a business (Use custom adapter)
     */
    public void test2(View view) {
        new BusinessPicker()
                .setOnSelected((business, business2) -> action(business.name + "/" + business2.name))
                .show(this);
    }

    /**
     * Test Pick a year and a month
     */
    public void test3(View view) {
        new DatePicker()
                .setOnSelected((s, s2) -> action(s + s2))
                .show(this);
    }

    private void action(String action) {
        ((TextView) findViewById(R.id.tv_show)).setText(action);
    }

    /**
     * 初始化城市数据库（第一次）
     */
    public void initDatabase() {
        if (App.db.queryCount(City.class) > 0) {
            return;
        }
        InputStream is = getResources().openRawResource(R.raw.city);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader reader2 = new BufferedReader(reader);
        String temp;
        try {
            while ((temp = reader2.readLine()) != null) {
                String args[] = temp.split("\\$");
                App.db.insert(new City(args[0], args[3], args[1], args[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化行业信息数据（第一次）
     */
    public void initBusinessDatabase() {
        if (App.db.queryCount(Business.class) > 0) {
            return;
        }
        InputStream is = getResources().openRawResource(R.raw.business);
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader reader2 = new BufferedReader(reader);
        String temp;
        try {
            while ((temp = reader2.readLine()) != null) {
                String args[] = temp.split("\\$");
                App.db.insert(new Business(args[0], args[1], args[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
