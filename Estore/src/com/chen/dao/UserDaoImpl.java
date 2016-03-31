package com.chen.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.chen.domain.User;
import com.chen.util.TransactionManager;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		String sql = "insert into users values(null,?,?,?,?,?,?,?,null)";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),user.getRole(),user.getState(),user.getActivecode());
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserByName(String username) {
		String sql = "select * from users where username = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),username);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void delUser(int id) {
		String sql = "delete from users where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}

	public User findUserByActivecode(String activecode) {
		String sql = "select * from users where activecode = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),activecode);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void updateState(int id, int state) {
		String sql = "update users set state = ? where id=?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			runner.update(sql,state,id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
	}

	public User finUserByNameAndPsw(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class) ,username ,password);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public User findUserById(int user_id) {
		String sql = "select * from users where id = ?";
		try{
			QueryRunner runner = new QueryRunner(TransactionManager.getSource());
			return runner.query(sql, new BeanHandler<User>(User.class),user_id);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
