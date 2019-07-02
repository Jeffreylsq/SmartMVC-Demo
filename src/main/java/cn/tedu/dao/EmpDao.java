package cn.tedu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Emp;

/**
 * Data access object class for emp
 * @author Tianyu Wei
 *
 */
public class EmpDao {
	
	/**
	 * Delete emp Info , if success return 1
	 * @param empno
	 * @return int 
	 */
	public int deleteEmp(Integer empno) {
		String sql = "delete from emp where empno=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, empno);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn); 
		}
	}
	
	/**
	 * Find empno which is max number
	 * @return int
	 */
	
	public Integer findMaxEmpno() {
		String sql = "select max(empno) as empno "
				+ "from emp";
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			Integer empno=0;
			while(rs.next()) {
				empno=rs.getInt("empno");
			}
			return empno;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * Find all jobs in the form which in the databases
	 * @return list<String>
	 */
	public List<String> findJobs(){
		String sql = "select distinct job from emp";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(sql);
			List<String> list = new ArrayList<String>();
			while(rs.next()) {
				list.add(rs.getString("job"));
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * Save emp Info in the databases
	 * @param emp new Info
	 */
	public void saveEmp(Emp emp) {
		String sql = "insert into emp "
				+ "( ename, job, mgr, "
				+ "hiredate, sal, comm, deptno, empno) "
				+ "values (?,?,?,?,?,?,?,?)"; 
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			
			setParams(emp, ps);
			
			int n = ps.executeUpdate();
			if(n!=1) {
				throw new RuntimeException("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}


	/**
	 * Find all emp Info in the List of Emp
	 * 
	 */
	public List<Emp> findAll(){
		Connection conn = null;
		try {
			//1. Connect to databases
			conn = DBUtil.getConnection();
			//2. execute sql 
			String sql = "select * from emp";
			Statement st=conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//3. Save rs data in the Emp list
			List<Emp> list=new ArrayList<Emp>();
			while(rs.next()) {
				Emp emp = empMapper(rs);
				list.add(emp);
 			}
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn); 
		}
	}


	private Emp empMapper(ResultSet rs) throws SQLException {
		int empno=rs.getInt("empno");
		String ename=rs.getString("ename");
		String job=rs.getString("job");
		int mgr = rs.getInt("mgr");
		Date hiredate=rs.getDate("hiredate");
		double sal = rs.getDouble("sal");
		double comm = rs.getDouble("comm");
		int deptno=rs.getInt("deptno");
		Emp emp=new Emp(empno, ename, job, 
			mgr, hiredate, sal, comm, deptno);
		return emp;
	}
	
	/**
	 * Find Emp Info by using empno
	 * @param empno
	 * @return a Emp instantiation
	 *      
	 */
	public Emp findEmpByEmpno(Integer empno) {
		String sql = "select * from emp where empno=?";
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, empno);
			ResultSet rs=ps.executeQuery();
			Emp emp = null;
			while(rs.next()) {
				emp = empMapper(rs);
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * Update Emp INFO, save change into databases 
	 * 
	 */
	public int updateEmp(Emp emp) {
		String sql="update emp "
				+ "set ename=?, job=?, mgr=?, "
				+ "hiredate=?, sal=?, comm=?, deptno=? "
				+ "where empno=?";
		Connection conn=null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			
			setParams(emp, ps);
			
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 *  This method is a optimize, extract from saveEmp method,
	 *  because in saveEmp ,use preparedStatement, we extract the parts of 
	 *  assigned value to prepareStatement
	 * @param emp
	 * @param ps
	 * @throws SQLException
	 */

	private void setParams(Emp emp, PreparedStatement ps) throws SQLException {
		ps.setString(1, emp.getEname());
		ps.setString(2, emp.getJob());
		ps.setInt(3, emp.getMgr());
		ps.setDate(4, emp.getHiredate());
		ps.setDouble(5, emp.getSal());
		ps.setDouble(6, emp.getComm());
		ps.setInt(7, emp.getDeptno());
		ps.setInt(8, emp.getEmpno());
	}
	

}
















