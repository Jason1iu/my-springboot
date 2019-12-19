package com.example.myspringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myspringboot.entity.Dept;
import com.example.myspringboot.service.DeptService;

@Service
public class DeptServiceImpl implements DeptService {
	
	private final String TABLENAME_TEST_DEPT = "test_dept";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Dept> getAllDept() {
		String sql = "select * from " + TABLENAME_TEST_DEPT + " where 1=1";
		RowMapper<Dept> rowMapper = BeanPropertyRowMapper.newInstance(Dept.class);
		List<Dept> depts = this.jdbcTemplate.query(sql, rowMapper);
		return depts;
	}
	
	@Override
	public Dept getDeptById(long id) {
		String sql = "select * from " + TABLENAME_TEST_DEPT + " where id=?";
		RowMapper<Dept> rowMapper = BeanPropertyRowMapper.newInstance(Dept.class);
		List<Dept> depts = this.jdbcTemplate.query(sql, new Object[] {id},rowMapper);
		Dept dept = null;
		if(!depts.isEmpty()) {
			dept = depts.get(0);
		}
		return dept;
	}

	@Override
	public Dept getDeptByDeptno(String deptno) {
		String sql = "select * from " + TABLENAME_TEST_DEPT + " where deptno=?";
		RowMapper<Dept> rowMapper = BeanPropertyRowMapper.newInstance(Dept.class);
		List<Dept> depts = this.jdbcTemplate.query(sql, new Object[] {deptno}, rowMapper);
		Dept dept = null;
		if(!depts.isEmpty()) {
			dept = depts.get(0);
		}
		return dept;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public long insertDept(Dept dept) {
		String sql = "insert into " + TABLENAME_TEST_DEPT +"(deptno, deptname, memo) values(?, ?, ?)";
		long index = this.jdbcTemplate.update(sql, dept.getDeptno(), dept.getDeptname(), dept.getMemo());
		return index;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public long updateDeptById(long id, Dept dept) {
		String sql = "update " + TABLENAME_TEST_DEPT +" set deptno=? deptname=?, memo=? where id=?";
		long index = this.jdbcTemplate.update(sql, dept.getDeptno(), dept.getDeptname(), dept.getMemo(), id);
		return index;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public long updateDeptByDeptno(String deptno, Dept dept) {
		String sql = "update " + TABLENAME_TEST_DEPT +" set deptname=?, memo=? where deptno=?";
		long index = this.jdbcTemplate.update(sql, dept.getDeptname(), dept.getMemo(), dept.getDeptno());
		return index;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public long deleteDeptById(long id) {
		String sql = "delete from " + TABLENAME_TEST_DEPT +" where id=?";
		long index = this.jdbcTemplate.update(sql, id);
		return index;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public long deleteDeptByDeptno(String deptno) {
		String sql = "delete from " + TABLENAME_TEST_DEPT +" where deptno=?";
		long index = this.jdbcTemplate.update(sql, deptno);
		return index;
	}

}
