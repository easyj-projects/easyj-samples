package org.easyj.spring.boot.samples.poi.excel.mockquery;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @author wangliang181230
 */
public class MyEntity {

	@Excel(name = "姓名", orderNum = "0")
	private String name;

	@Excel(name = "年龄", orderNum = "1")
	private Integer age;

	@Excel(name = "出生日期", orderNum = "2")
	private Date birthday;

	// 测试无注解的情况
	private String desc;


	public MyEntity() {
	}

	public MyEntity(String name, Integer age, Date birthday, String desc) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
		this.desc = desc;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
