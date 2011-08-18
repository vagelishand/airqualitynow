package com.quadrictech.airqualitynow.db;

import java.sql.SQLException;
import java.util.List;

public interface IGenericRepository<T> {
	public List<T> getAll() throws SQLException ;
	public T getById(int id) throws SQLException ;
	public void insert(T entity) throws SQLException ;
	public void update(T entity) throws SQLException ;
	public void delete(int id) throws SQLException ;
	public List<T> getByFieldEquals(String fieldName, Object value)throws SQLException ;
}
