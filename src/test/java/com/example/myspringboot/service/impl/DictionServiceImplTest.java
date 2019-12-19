package com.example.myspringboot.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.myspringboot.entity.Diction;
import com.example.myspringboot.entity.Item;
import com.example.myspringboot.service.DictionService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DictionServiceImplTest {
	
	private DictionService dictionService;

	@Test
	public void testGetDitions() {
		List<Diction> dictions = this.dictionService.getDictions();
		System.out.println("start");
		for(Diction d : dictions) {
			System.out.println(d);
		}
		System.out.println("Yes");
	}

	@Test
	public void testGetDitionString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDitionLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertDictionItem() {
		Diction diction = new Diction();
		diction.setDicttable("baobiao_dict_fenlei");
		Item item = new Item();
		item.setCode("0");
		item.setMean("测试用例1");
		item.setExt1("stop");
		this.dictionService.insertDictionItem(diction, item);
		System.out.println("Yes");
	}

	@Test
	public void testUpdateDictionItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDictionItem() {
		fail("Not yet implemented");
	}

}
