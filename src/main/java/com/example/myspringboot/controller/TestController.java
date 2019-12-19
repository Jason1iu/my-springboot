package com.example.myspringboot.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myspringboot.entity.Diction;
import com.example.myspringboot.entity.Item;
import com.example.myspringboot.service.DictionService;

import io.swagger.annotations.ApiOperation;

/**
 * 测试
 * @author LiuJie
 *
 */
@RestController
public class TestController {
	
    private final static Log logger = LogFactory.getLog(TestController.class);
	
	@Autowired
	private DictionService dictionService;

	@GetMapping("hello")
	public String test() { 
		return "hello world!";
	}
	
	@ApiOperation(value="获取所有的代码字典集合", notes="代码字典集合")
	@GetMapping("/dictions")
	public List<Diction> getDictions() { 
		return this.dictionService.getDictions();
	}
	
	@ApiOperation(value="获取指定的代码字典", notes="根据代码字典的name")
	@GetMapping("/diction/{name}")
	public Diction getDiction(@PathVariable String name) { 
		return this.dictionService.getDiction(name);
	}
	
	@ApiOperation(value="获取指定代码字典的所有词条", notes="根据代码字典的name")
	@GetMapping("/diction/{name}/items")
	public List<Item> getDictionItems(@PathVariable String name) { 
		return this.dictionService.getDictionItems(name);
	}
	
	@PostMapping
	public void insertDictionItem() {
		try {
			Diction diction = new Diction();
			diction.setDicttable("baobiao_dict_fenlei");
			Item item = new Item();
			item.setCode("0");
			item.setMean("测试用例1");
			item.setExt1("stop");
			this.dictionService.insertDictionItem(diction, item);
		}
		catch(Exception e) {
			logger.debug(e.getMessage(), e);
		}
	}
}
