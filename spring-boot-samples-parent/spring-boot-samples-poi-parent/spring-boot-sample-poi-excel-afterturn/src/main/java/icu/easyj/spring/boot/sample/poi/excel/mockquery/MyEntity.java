package icu.easyj.spring.boot.sample.poi.excel.mockquery;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author wangliang181230
 */
public class MyEntity {

	@Excel(name = "姓名", orderNum = "0")
	private String name;

	@Excel(name = "年龄", orderNum = "1")
	private Integer age;

	@Excel(name = "出生日期", orderNum = "2")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
	@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
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
