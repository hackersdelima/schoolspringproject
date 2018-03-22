package com.school.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.school.dao.UserDao;
import com.school.model.UserModel;

@Component
public class UserDaoImpl implements UserDao{
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		System.out.println("ksjdflkjsdlkfj");
	}
	
	public UserModel verifyUser(UserModel user)
	{
		
		String sql="SELECT * FROM USERTBL WHERE USERNAME='"+user.getUsername()+"' AND PASSWORD='"+user.getPassword()+"'";
		return jdbcTemplate.queryForObject(sql,new ClassMapper());
		
	}
	
	
	public static final class ClassMapper implements RowMapper<UserModel>{

		@Override
		public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserModel user=new UserModel();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setUserid(rs.getString("userid"));
			user.setStatus(rs.getString("status"));
			
			
			return user;
		}
		
	}

}
