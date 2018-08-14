package com.ott.installation.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ott.installation.constant.Constant;
import com.ott.installation.domain.DataBaseProperties;

import org.springframework.stereotype.Service;

/**
 * ConfigService
 */
@Service
public class ConfigService {


    public boolean testDataBase(DataBaseProperties dataBase) {
        try {
            Class.forName(Constant.JDBC_DRIVER);
            String url = new StringBuffer().append(Constant.URL_PREFIX).append(dataBase.getHostName()).append(Constant.DATA_SOURCE_NAME).toString();
            Connection connection = DriverManager.getConnection(url, dataBase.getUserName(), dataBase.getPassword());
            if (!connection.isClosed()) {
                connection.close();
                return true;
            }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return false;
    }
}