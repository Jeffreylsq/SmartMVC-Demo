package cn.tedu.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/**
 *  Encapsulate connection between databases and program
 *  @author Tianyu Wei
 */
public class DBUtil {
	private static BasicDataSource ds;
	
	/*
	 * Use Properties document initialize databases connection pool
	 * In this way, we can do modify in properties file other that change codes
	 */
	static {
		try {
			Properties cfg=new Properties();
			InputStream in = DBUtil.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
			cfg.load(in);
			String driver=cfg.getProperty("driver");
			String url=cfg.getProperty("url");
			String user=cfg.getProperty("username");
			String pwd=cfg.getProperty("password");
			ds=new BasicDataSource();
			ds.setDriverClassName(driver);
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
    /**
     * Get connection from database connection pool			
     * @return connection
     */
	public static Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	/**
	 * Encapsulate close() in close method which is static
	 * @param conn
	 */
	public static void close(Connection conn) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}




