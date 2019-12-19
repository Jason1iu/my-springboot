package com.example.myspringboot.controller;

/**
 * 返回结果Json数据
 * @author LiuJie
 *
 */
public class ResultJson {
	
	private boolean success = false;
	
	private Object message = null;
	
	private Object data = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
