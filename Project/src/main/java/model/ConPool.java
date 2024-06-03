package model;

import java.sql.*;
import org.apache.tomcat.jdbc.pool.*;
import java.util.*;

public class ConPool {
	private static DataSource datasource;
	
	private ConPool() {
	    throw new IllegalStateException("Utility class");
	  }
	
	public static Connection getConnection() throws SQLException{
		if(datasource == null) {
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:mysql://localhost:3306/PeekABook?serverTimezone=Europe/Rome");
			p.setDriverClassName("com.mysql.cj.jdbc.Driver");
			p.setUsername("root");
			p.setPassword("root");
			p.setMaxActive(100);
			p.setInitialSize(10);
			p.setMinIdle(10);
			p.setRemoveAbandoned(true);
			p.setRemoveAbandonedTimeout(60);
			datasource = new DataSource();
			datasource.setPoolProperties(p);
		}
		return datasource.getConnection();
	}
}
