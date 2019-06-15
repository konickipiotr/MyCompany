package pl.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import pl.mycompany.conectorprovider.ConectorProvider;
import pl.mycompany.model.Worker;

public class EmpDao {
	private final static String CREATE="INSERT INTO employees (firstName, lastName, department, position, salary ) VALUES (?,?,?,?,?); ";
	private final static String DISP_ALL = "SELECT * FROM employees;";
	private final static String UPDATE =  "UPDATE employees SET firstName = ?, lastName = ?, department = ?, position = ?, salary = ? WHERE id = ?; ";
	private final static String DELETE = "DELETE FROM employees WHERE id=?; ";
	private final static String DROP = "DROP TABLE employees; ";
	private final static String CREATETABLE = "CREATE TABLE `mycompany`.`employees` (" + 
			"  `id` INT NOT NULL AUTO_INCREMENT," + 
			"  `firstName` VARCHAR(45) NOT NULL," + 
			"  `lastName` VARCHAR(45) NOT NULL," + 
			"  `department` VARCHAR(45) NOT NULL," + 
			"  `position` VARCHAR(45) NOT NULL," + 
			"  `salary` DOUBLE NOT NULL," + 
			"  PRIMARY KEY (`id`)," + 
			"  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
	
	public void resetTable() throws SQLException {
		try(Connection conn = ConectorProvider.getConnection(); PreparedStatement pstm = conn.prepareStatement(DROP);  PreparedStatement pstm2 = conn.prepareStatement(CREATETABLE);){
			pstm.executeUpdate();
			pstm2.executeUpdate();
		}
	}
	
	public void update(Worker worker) throws SQLException{
		try(Connection conn = ConectorProvider.getConnection();
				PreparedStatement pstm = conn.prepareStatement(UPDATE);){
			
			pstm.setString(1, worker.getFirstName());
			pstm.setString(2, worker.getLastName());
			pstm.setString(3, worker.getDepartment());
			pstm.setString(4, worker.getPosition());
			pstm.setDouble(5, worker.getSalary());
			pstm.setInt(6, worker.getId());
			pstm.executeUpdate();
		}
	}
	
	public void delete(int id) throws SQLException {
		try(Connection conn = ConectorProvider.getConnection(); PreparedStatement pstm = conn.prepareStatement(DELETE);){
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}
	}
	
	public void create(Worker worker) throws SQLException{
		try(Connection conn = ConectorProvider.getConnection();
				PreparedStatement pstm = conn.prepareStatement(CREATE);){
			pstm.setString(1, worker.getFirstName());
			pstm.setString(2, worker.getLastName());
			pstm.setString(3, worker.getDepartment());
			pstm.setString(4, worker.getPosition());
			pstm.setDouble(5, worker.getSalary());
			pstm.executeUpdate();
		}
	}
	
	public Worker readWorker(int equ) throws SQLException{
		Worker worker = null;
		String READ = "SELECT id, firstName, lastName, department, position, salary FROM employees WHERE id = ?;";
		try(Connection conn = ConectorProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(READ);	){
			pst.setInt(1, equ);
			System.out.println(pst);
			
			ResultSet resultSet = pst.executeQuery();
			if(resultSet.next()){	
				worker = new Worker();
				worker.setId(resultSet.getInt("id"));
				worker.setFirstName(resultSet.getString("firstName"));
				worker.setLastName(resultSet.getString("lastName"));
				worker.setDepartment(resultSet.getString("department"));
				worker.setPosition(resultSet.getString("position"));
				worker.setSalary(resultSet.getDouble("salary"));
			}
		}
		return worker;
	}
	
	
	public List<Worker> read(String what, String equ) throws SQLException{
		List<Worker> resultList = new ArrayList<>();
		String READ = "SELECT id, firstName, lastName, department, position, salary   FROM employees WHERE ";
		READ+=what;
		READ+="=?;";
		try(Connection conn = ConectorProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(READ);	){
			pst.setString(1, equ);
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()){
				Worker worker = new Worker();
				worker.setId(resultSet.getInt("id"));
				worker.setFirstName(resultSet.getString("firstName"));
				worker.setLastName(resultSet.getString("lastName"));
				worker.setDepartment(resultSet.getString("department"));
				worker.setPosition(resultSet.getString("position"));
				worker.setSalary(resultSet.getDouble("salary"));
				resultList.add(worker);
			}
		}
		return resultList;
	}
	
	public List<Worker> dispAll() throws SQLException{
		List<Worker> resultList = new ArrayList<>();
		
		try(Connection conn = ConectorProvider.getConnection();
			PreparedStatement pst = conn.prepareStatement(DISP_ALL);	){
			ResultSet resultSet = pst.executeQuery();
			while(resultSet.next()){
				Worker worker = new Worker();
				worker.setId(resultSet.getInt("id"));
				worker.setFirstName(resultSet.getString("firstName"));
				worker.setLastName(resultSet.getString("lastName"));
				worker.setDepartment(resultSet.getString("department"));
				worker.setPosition(resultSet.getString("position"));
				worker.setSalary(resultSet.getDouble("salary"));
				resultList.add(worker);
			}
		}
		return resultList;
	}
}
