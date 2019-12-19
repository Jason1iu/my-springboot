package com.example.myspringboot.entity;

//import lombok.Data;

//@Data
public class Diction {
	public long dictid;
	public String name;
	public String caption;
	public long codelength;
	public String type;
	public String levels;
	public boolean leafonly;
	public String dicttable;
	public String memo;
	public String ext1;
	public String ext2;
	public String ext3;

	public long getDictid() {
		return dictid;
	}

	public void setDictid(long dictid) {
		this.dictid = dictid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public long getCodelength() {
		return codelength;
	}

	public void setCodelength(long codelength) {
		this.codelength = codelength;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevels() {
		return levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	public boolean isLeafonly() {
		return leafonly;
	}

	public void setLeafonly(boolean leafonly) {
		this.leafonly = leafonly;
	}

	public String getDicttable() {
		return dicttable;
	}

	public void setDicttable(String dicttable) {
		this.dicttable = dicttable;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
}
