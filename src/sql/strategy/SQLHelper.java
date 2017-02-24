package sql.strategy;

import sql.ConnectionFactory;

/**
 * Created by BORIS on 24.02.17.
 */
public interface SQLHelper
  {
	void getStrategy(ConnectionFactory connectionFactory, String sql);
  }
