package sql.strategy;

import sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by BORIS on 24.02.17.
 */
public class Activity implements SQLHelper
 {
   @Override
   public void getStrategy(ConnectionFactory connectionFactory, String sql)
     {
	   try (Connection conn = connectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql))
	     {
		   
	     }
	   catch (SQLException e)
	     {
		   e.printStackTrace();
	     }
	 }
 }
