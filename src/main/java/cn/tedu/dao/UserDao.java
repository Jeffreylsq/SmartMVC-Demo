package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.entity.User;

/**
 * Data access object class for user
 * @author Tianyu Wei
 *
 */
public class UserDao {
	
	/**
	 * Method that find user based on username and password
	 * @param username
	 * @param password
	 * @return true or false
	 */
	public boolean getUserByUAP(String username,String password) {
		String sql="select * from user where username=? and password=?";
		
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Check username exist or not
	 * @param username
	 * @return true-username exist false-not exist
	 */
	public boolean getUserByUsername(String username) {
		String sql="select * from user where username=?";
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)){
			
			ps.setString(1, username);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public boolean saveUser(User user) {
		String sql="insert into user values(null,?,?)";
		try(Connection conn=DBUtil.getConnection();
				PreparedStatement ps=conn.prepareStatement(sql)
				){
			// Save username and password
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			// execute insert action 
			int n=ps.executeUpdate();
			if(n==1) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
