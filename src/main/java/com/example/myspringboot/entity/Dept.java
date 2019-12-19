package com.example.myspringboot.entity;

public class Dept {
	public long id;
	public String deptno;
	public String deptname;
	public String memo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptno() {
		return deptno;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", deptname=" + deptname + ", memo=" + memo + "]";
	}
}
