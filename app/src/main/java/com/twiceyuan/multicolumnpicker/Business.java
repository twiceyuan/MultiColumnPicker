package com.twiceyuan.multicolumnpicker;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;

/**
 * Created by twiceYuan on 9/8/15.
 *
 * 行业信息
 */
@Table("business")
public class Business {

    @Column("id")
    @PrimaryKey(PrimaryKey.AssignType.BY_MYSELF)
    public String id;

    @Column("name")
    public String name;

    @Column("parent")
    public String parent;

    public Business(String id, String name, String parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }
}
