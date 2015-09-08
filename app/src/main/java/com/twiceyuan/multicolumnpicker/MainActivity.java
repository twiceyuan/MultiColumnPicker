package com.twiceyuan.multicolumnpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.twiceyuan.library.MultiColumnPicker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
    }

    public void test(View view) {
        MultiColumnPicker<City, City> picker = new MultiColumnPicker<>(this);
        picker.setLeftContent(left());
        picker.setOnLeftSelected((position, city) -> right(city));
        picker.setOnRightSelected((position, city) -> action(city));
        picker.setMapLeftString(city -> city.name);
        picker.setMapRightString(city -> city.name);
        picker.setMapLeftId(city -> city.id);
        picker.setMapRightId(city -> city.id);
        picker.setLeftDefault("江苏");
        picker.show();
    }

    private List<City> left() {
        return App.db.query(
                QueryBuilder.create(City.class).whereEquals("parent", "86").orderBy("id"));
    }

    private List<City> right(City city) {
        return App.db.query(QueryBuilder.create(City.class).whereEquals("parent", city.id));
    }

    private void action(City city) {
        Toast.makeText(this, city.fullName + city.id, Toast.LENGTH_SHORT).show();
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
}
