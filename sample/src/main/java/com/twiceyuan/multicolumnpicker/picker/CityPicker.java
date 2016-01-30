package com.twiceyuan.multicolumnpicker.picker;

import android.content.Context;

import com.litesuits.orm.db.assit.QueryBuilder;
import com.twiceyuan.library.MultiColumnPicker;
import com.twiceyuan.multicolumnpicker.App;
import com.twiceyuan.multicolumnpicker.City;

import java.util.List;

/**
 * Created by twiceYuan on 9/9/15.
 *
 * City Picker
 */
public class CityPicker {

    OnSelected<City, City> mOnSelected;

    public void show(Context context) {
        MultiColumnPicker<City, City> picker = new MultiColumnPicker<>(context);
        picker.setLeftContent(getProvince());
        picker.setOnLeftSelected((position, city) -> getCity(city));
        // don't use lambda
        //noinspection Convert2MethodRef
        picker.setOnRightSelected((city1, city2) -> mOnSelected.onSelect(city1, city2));
        picker.setMapLeftString(city -> city.name);
        picker.setMapRightString(city -> city.fullName);
        picker.setMapLeftId(city -> city.id);
        picker.setMapRightId(city -> city.id);
        picker.setLeftDefaultString("江苏");
        picker.show();
    }

    private List<City> getProvince() {
        return App.db.query(
                QueryBuilder.create(City.class).whereEquals("parent", "86").orderBy("id"));
    }

    private List<City> getCity(City city) {
        return App.db.query(QueryBuilder.create(City.class).whereEquals("parent", city.id));
    }

    public CityPicker setOnSelected(OnSelected<City, City> onSelected) {
        mOnSelected = onSelected;
        return this;
    }
}
