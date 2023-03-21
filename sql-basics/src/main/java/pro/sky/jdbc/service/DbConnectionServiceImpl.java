package pro.sky.jdbc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pro.sky.jdbc.configuration.FilesProperties;
import pro.sky.jdbc.exception.ConnectionException;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
@Service
@RequiredArgsConstructor
public class DbConnectionServiceImpl implements DbConnectionService {

    private final FilesProperties filesProperties;
    private Connection connection;

    @Override
    @Nullable
    public Connection getConnection() {
        return this.connection;
    }

    @PostConstruct
    private void init() throws ConnectionException {
        try {
            this.connection = DriverManager
                    .getConnection(filesProperties.url(), filesProperties.user(), filesProperties.password());
        } catch (SQLException e) {
            throw new ConnectionException("Ошибка соединения с БД: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        try {
            String message;
            if (connection != null && connection.getMetaData() != null) {
                message = connection.getMetaData().getDatabaseProductName() + " "
                        + connection.getMetaData().getDatabaseProductVersion();
            } else {
                message = "не установлено";
            }
            return "Соединение с БД: " + message;
        } catch (SQLException e) {
            return "Ошибка соединения с БД: " + e.getMessage();
        }
    }
}
