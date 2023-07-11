package com.strabag.fileParsingApp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;
//import com.mysql.cj.jdbc.Driver;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

	/* No need to define the dataSource() method here
    @Bean
    public DataSource dataSource() {
    	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        //dataSource.setDriver("com.mysql.cj.jdbc.Driver");
    	dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/strabagInv");
        dataSource.setUsername("root");
        dataSource.setPassword("Lekshmi@123");
        return dataSource;
    }
    */
	
		private String url;
	    private String username;
	    private String password;
	    
	 // Getters and setters for the properties
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

}
