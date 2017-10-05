package com.example.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;


@RestController
public class DataSourceController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/druidDataSource")
    public String dataSourcePage() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println(dataSource);
        return "DataSource";
    }
}

