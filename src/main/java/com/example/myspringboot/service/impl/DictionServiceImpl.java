package com.example.myspringboot.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myspringboot.entity.Diction;
import com.example.myspringboot.entity.Item;
import com.example.myspringboot.service.DictionService;

/**
 * 代码字典服务类接口实现
 * @author LiuJie
 *
 */
@Service
public class DictionServiceImpl implements DictionService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public List<Diction> getDictions() {
		DictionDBTable dictionDBTable = new DictionDBTable();
		String tableName = dictionDBTable.getTableName();
		String sql = "select * from " + tableName + " where 1=1";
		RowMapper<Diction> rowMapper = BeanPropertyRowMapper.newInstance(Diction.class);
		List<Diction> dictions = this.jdbcTemplate.query(sql, rowMapper);
		return dictions;
	}

	@Override
	@Transactional
	public Diction getDiction(String name) {
		DictionDBTable dictionDBTable = new DictionDBTable();
		String tableName = dictionDBTable.getTableName();
		String sql = "select * from " + tableName + " where name=?";
        List<Diction> dictions = jdbcTemplate.query(sql, new Object[] { name }, new DictionRowMapper());
        Diction diction = null;
        if(!dictions.isEmpty()) {
        	diction = dictions.get(0);
        }
        return diction;
	}

	@Override
	@Transactional
	public Diction getDiction(long dictid) {
		DictionDBTable dictionDBTable = new DictionDBTable();
		String tableName = dictionDBTable.getTableName();
		String sql = "select * from " + tableName + " where dictid=?";
        List<Diction> dictions = jdbcTemplate.query(sql, new Object[] { dictid }, new DictionRowMapper());
        Diction diction = null;
        if(!dictions.isEmpty()) {
        	diction = dictions.get(0);
        }
        return diction;
	}

	@Override
	@Transactional
	public List<Item> getDictionItems(String name) {
		Diction diction = this.getDiction(name);
		String tableName = diction.getDicttable();
		String sql = "select * from " + tableName + " where 1=1";
		RowMapper<Item> rowMapper = BeanPropertyRowMapper.newInstance(Item.class);
        List<Item> items = jdbcTemplate.query(sql, rowMapper);
        return items;
	}

	@Override
	@Transactional
	public List<Item> getDictionItems(long dictid) {
		Diction diction = this.getDiction(dictid);
		String tableName = diction.getDicttable();
		String sql = "select * from " + tableName + " where 1=1";
		RowMapper<Item> rowMapper = BeanPropertyRowMapper.newInstance(Item.class);
        List<Item> items = jdbcTemplate.query(sql, rowMapper);
        return items;
	}
	
	@Override
	@Transactional
	public void insertDictionItem(Diction diction, Item item) {
		String sql = "insert into " + diction.getDicttable() +"(code, mean, ext1) values(?, ?, ?)";
		this.jdbcTemplate.update(sql, item.getCode(), item.getMean(), item.getExt1());
	}

	@Override
	@Transactional
	public void updateDictionItem(Diction diction, Item item) {
		String sql = "update " + diction.getDicttable() +" set mean = ?, ext1 = ? where code = ?";
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, item.getMean());
				ps.setString(2, item.getExt1());
				ps.setString(3, item.getCode());
			}
		};
		this.jdbcTemplate.update(sql, pss);
	}

	@Override
	@Transactional
	public void deleteDictionItem(Diction diction, String code) {
		String sql = "delete from " + diction.getDicttable() +" where code = ?";
		this.jdbcTemplate.update(sql, code);
	}
}

class DictionRowMapper implements RowMapper<Diction>{
	
	@Override
	public Diction mapRow(ResultSet rs, int rowNum) throws SQLException {
		Diction d = new Diction();
		d.setDictid(rs.getLong("dictid"));
		d.setName(rs.getString("name"));
		d.setCaption(rs.getString("caption"));
		d.setCodelength(rs.getLong("codelength"));
		d.setType(rs.getString("type"));
		d.setLevels(rs.getString("levels"));
		d.setLeafonly(rs.getBoolean("leafonly"));
		d.setDicttable(rs.getString("dicttable"));
		d.setMemo(rs.getString("memo"));
		d.setExt1(rs.getString("ext1"));
		d.setExt2(rs.getString("ext2"));
		d.setExt3(rs.getString("ext3"));
		return d;
	}
}

class ItemRowMapper implements RowMapper<Item>{
	
	@Override
	public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
		Item d = new Item();
		d.setCode(rs.getString("code"));
		d.setMean(rs.getString("mean"));
		d.setMemo(rs.getString("memo"));
		d.setExt1(rs.getString("ext1"));
		d.setExt2(rs.getString("ext2"));
		d.setExt3(rs.getString("ext3"));
		return d;
	}
}
