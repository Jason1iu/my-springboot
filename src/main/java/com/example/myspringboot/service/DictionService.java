package com.example.myspringboot.service;

import java.util.List;

import com.example.myspringboot.entity.Diction;
import com.example.myspringboot.entity.Item;

/**
 * 代码字典服务类接口
 * 
 * @author LiuJie
 *
 */
public interface DictionService {

	/**
	 * 获取所有的代码字典集合
	 * 
	 * @return
	 */
	public List<Diction> getDictions();

	/**
	 * 获取指定的代码字典
	 * 
	 * @return
	 */
	public Diction getDiction(String name);

	public Diction getDiction(long dictid);
	
	/**
	 * 获取指定的代码字典里的所有词条
	 * 
	 * @return
	 */
	public List<Item> getDictionItems(String name);

	public List<Item> getDictionItems(long dictid);

	/**
	 * 在指定代码字典添加词条
	 * 
	 * @param diction
	 * @param item
	 */
	public void insertDictionItem(Diction diction, Item item);

	/**
	 * 在指定代码字典修改词条
	 * 
	 * @param diction
	 * @param item
	 */
	public void updateDictionItem(Diction diction, Item item);

	/**
	 * 在指定代码字典删除词条
	 * 
	 * @param diction
	 * @param item
	 */
	public void deleteDictionItem(Diction diction, String code);
}
