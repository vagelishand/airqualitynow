package com.devterous.aqn.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Art on 1/26/2015.
 */
@DatabaseTable
public class Category {
    Category(){
    }

    public Category(String name){
        Name = name;
    }

    @DatabaseField(columnName ="Id", generatedId = true)
    public int Number;
    @DatabaseField
    public String Name;
}
