package com.example.myspringboot.service;

import java.util.List;

import com.example.myspringboot.entity.Dept;

public interface DeptService {
	public List<Dept> getAllDept();
	
	public Dept getDeptById(long id);

	public Dept getDeptByDeptno(String deptno);

	public long insertDept(Dept dept);

	public long updateDeptById(long id, Dept dept);

	public long updateDeptByDeptno(String deptno, Dept dept);

	public long deleteDeptById(long id);

	public long deleteDeptByDeptno(String deptno);
}
