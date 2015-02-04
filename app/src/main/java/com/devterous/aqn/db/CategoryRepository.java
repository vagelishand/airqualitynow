package com.devterous.aqn.db;

import com.devterous.aqn.model.Category;
import com.devterous.aqn.model.Pollutant;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Art on 1/26/2015.
 */
public class CategoryRepository extends  GenericRepository<Category> {
    public CategoryRepository(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Category.class);
    }
}
