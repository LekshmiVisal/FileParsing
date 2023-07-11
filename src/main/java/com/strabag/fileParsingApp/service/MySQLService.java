package com.strabag.fileParsingApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.strabag.fileParsingApp.config.DataSourceConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class MySQLService {

    private final DataSourceConfig dataSourceConfig;
    

    @Autowired
    public MySQLService(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }


 /*   public void executeQuery() throws SQLException {
        try (Connection connection = dataSourceConfig.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM my_table");
            // Execute the query and process the results
        }
        
    }
    */
}
