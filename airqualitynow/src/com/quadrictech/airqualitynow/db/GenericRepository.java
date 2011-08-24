package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public abstract class GenericRepository<T> implements IGenericRepository<T> {
	AppDao<T> mDao;
	
	public GenericRepository(ConnectionSource connectionSource,
			Class<T> dataClass) throws SQLException {
		mDao = new AppDao<T>(connectionSource, dataClass);
	}
	
	public List<T> getAll() throws SQLException {
		return mDao.queryForAll();
	}

	public T getById(int id)  throws SQLException {
		return mDao.queryForId(id);
	}

	public void insert(T entity)  throws SQLException {
		mDao.create(entity);
	}

	public void update(T entity)  throws SQLException {
		mDao.update(entity);		
	}

	public void delete(int id)  throws SQLException {
		T entity = mDao.queryForId(id);
		mDao.delete(entity);		
	}

	public List<T> getByFieldEquals(String fieldName, Object value) throws SQLException {
		return mDao.queryForEq(fieldName, value);
	}

	public QueryBuilder<T, Integer> getQueryBuilder(){
		return mDao.queryBuilder();
	}
	
	public List<T> getQueryResults(PreparedQuery<T> preparedQuery) throws SQLException{
		return mDao.query(preparedQuery);
	}
}
