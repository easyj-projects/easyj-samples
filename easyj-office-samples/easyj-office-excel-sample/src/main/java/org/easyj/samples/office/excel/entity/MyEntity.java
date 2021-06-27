package org.easyj.samples.office.excel.entity;

import java.util.Date;

import org.easyj.office.excel.annotation.ExcelAnnotation;
import org.easyj.office.excel.annotation.ExcelCellAnnotation;

/**
 * @author wangliang181230
 */
@ExcelAnnotation
public class MyEntity {

	@ExcelCellAnnotation(headName = "姓名", cellNum = 0)
	private String name;

	@ExcelCellAnnotation(headName = "年龄", cellNum = 1)
	private Integer age;

	@ExcelCellAnnotation(headName = "出生日期", cellNum = 2)
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
}
