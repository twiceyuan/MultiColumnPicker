package com.twiceyuan.multicolumnpicker;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by twiceYuan on 9/7/15.
 *
 * 城市（也可以是省份）
 */
@Table("city")
public class City {

    @Column("id")
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    public String id;

    @Column("name")
    @NotNull
    public String name;

    @Column("full_name")
    @NotNull
    public String fullName;

    @Column("parent")
    @NotNull
    public String parent;

    public City(String id, String name, String fullName, String parent) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.parent = parent;
    }
}
