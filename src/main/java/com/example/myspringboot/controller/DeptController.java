package com.example.myspringboot.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myspringboot.entity.Dept;
import com.example.myspringboot.service.DeptService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/test/dept")
public class DeptController {
	private final static Log logger = LogFactory.getLog(DeptController.class);

	@Autowired
	private DeptService deptService;

	@ApiOperation(value = "获取全部的部门数据", notes = "部门数据List")
	@GetMapping
	public ResponseEntity<ResultJson> getAllDept() {
		ResultJson ret = new ResultJson();
		try {
			List<Dept> depts = this.deptService.getAllDept();
			ret.setSuccess(true);
			ret.setData(depts);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
		}

		return ResponseEntity.ok(ret);
	}

//	@ApiOperation(value="获取指定部门数据", notes="通过id")
//	@GetMapping("/{id}")
//	public ResponseEntity<ResultJson> getDeptByDeptno(@PathVariable long id){
//		ResultJson ret = new ResultJson();
//		try {
//			Dept dept = this.deptService.getDeptById(id);
//			ret.setSuccess(true);
//			ret.setData(dept);
//		}
//		catch(Exception e) {
//			logger.debug(e.getMessage(), e);
//			ret.setSuccess(false);
//			ret.setMessage(e.getMessage());
//		}
//		
//		return ResponseEntity.ok(ret);
//	}

	@ApiOperation(value = "获取指定部门数据", notes = "通过部门编码deptno")
	@GetMapping("/{deptno}")
	public ResponseEntity<ResultJson> getDeptByDeptno(@PathVariable String deptno) {
		ResultJson ret = new ResultJson();
		try {
			Dept dept = this.deptService.getDeptByDeptno(deptno);
			ret.setSuccess(true);
			ret.setData(dept);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
		}

		return ResponseEntity.ok(ret);
	}

	@ApiOperation(value = "新建", notes = "添加部门数据")
	@PostMapping
	public ResponseEntity<ResultJson> insertDept(@RequestBody Dept dept) {
		ResultJson ret = new ResultJson();
		try {
			Dept d = this.deptService.getDeptByDeptno(dept.getDeptno());
			if (d != null) {
				throw new Exception("部门编码已存在！");
			}
			this.deptService.insertDept(dept);
			ret.setSuccess(true);
			ret.setData(this.deptService.getDeptByDeptno(dept.getDeptno()));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
		}

		return ResponseEntity.ok(ret);
	}

//	@ApiOperation(value="修改", notes="修改指定id的部门数据")
//	@PutMapping("/{id}")
//	public ResponseEntity<ResultJson> updateDeptById(@PathVariable long id, @RequestBody Dept dept){
//		ResultJson ret = new ResultJson();
//		try {
//			Dept d = this.deptService.getDeptByDeptno(dept.getDeptno());
//			if(d.getId() != id) {
//				throw new Exception("部门编码已存在！");
//			}
//			this.deptService.updateDeptById(id, dept);
//			ret.setSuccess(true);
//			ret.setData(this.deptService.getDeptById(id));
//		}
//		catch(Exception e) {
//			logger.debug(e.getMessage(), e);
//			ret.setSuccess(false);
//			ret.setMessage(e.getMessage());
//		}
//		
//		return ResponseEntity.ok(ret);
//	}

	@ApiOperation(value = "修改", notes = "修改指定部门编码deptno的部门数据")
	@PutMapping("/{deptno}")
	public ResponseEntity<ResultJson> updateDeptByDeptno(@PathVariable String deptno, @RequestBody Dept dept) {
		ResultJson ret = new ResultJson();
		try {
			if (deptno != dept.getDeptno()) {
				throw new Exception("不允许修改部门编码！");
			}
			this.deptService.updateDeptByDeptno(deptno, dept);
			ret.setSuccess(true);
			ret.setData(this.deptService.getDeptByDeptno(deptno));
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
		}

		return ResponseEntity.ok(ret);
	}

	@ApiOperation(value = "删除", notes = "删除多个指定部门编码deptno的部门数据，如deptnos='no1,no2,no3'")
	@DeleteMapping("/{deptnos}")
	public ResponseEntity<ResultJson> deleteDeptByDeptnos(@PathVariable String deptnos) {
		ResultJson ret = new ResultJson();
		try {
			String[] nos = deptnos.split(",");
			for (String deptno : nos) {
				this.deptService.deleteDeptByDeptno(deptno);
			}

			ret.setSuccess(true);
			ret.setData(this.deptService.getAllDept());
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			ret.setSuccess(false);
			ret.setMessage(e.getMessage());
		}

		return ResponseEntity.ok(ret);
	}

//	@ApiOperation(value="删除", notes="删除多个指定id的部门数据，如ids='id1,id2,id3'")
//	@DeleteMapping("/{ids}")
//	public ResponseEntity<ResultJson> deleteDeptByIds(@PathVariable String ids){
//		ResultJson ret = new ResultJson();
//		try {
//			String[] idList = ids.split(",");
//			for(String id: idList) {
//				this.deptService.deleteDeptById(Long.parseLong(id));
//			}
//			
//			ret.setSuccess(true);
//			ret.setData(this.deptService.getAllDept());
//		}
//		catch(Exception e) {
//			logger.debug(e.getMessage(), e);
//			ret.setSuccess(false);
//			ret.setMessage(e.getMessage());
//		}
//		
//		return ResponseEntity.ok(ret);
//	}
}