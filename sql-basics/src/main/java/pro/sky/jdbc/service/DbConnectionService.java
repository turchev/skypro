package pro.sky.jdbc.service;

import pro.sky.jdbc.exception.ConnectionException;

import java.sql.Connection;

public interface DbConnectionService {
     Connection getConnection() throws ConnectionException;
}
