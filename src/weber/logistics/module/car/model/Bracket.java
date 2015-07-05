package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class Bracket implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotEmpty(message = "{bracket.plateNum.null}")
	@Length(min = 7, max = 7, message = "{bracket.plateNum.length.illegal}")
	@Pattern(regexp = "^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$", message = "{bracket.code.illegal}")
	private String plateNum = null;

	@NotEmpty(message = "{bracket.name.null}")
	@Length(min = 1, max = 15, message = "{bracket.name.length.illegal}")
	private String name = null;
	
	@NotNull(message = "{bracket.size.null}")
	@Range(min = 0, max = 100, message = "{bracket.size.illegal}")
	private Integer size = 0;

	@NotNull(message = "{bracket.buyDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buyDate = null;

	@NotNull(message = "{bracket.price.null}")
	@Range(min = 0, max = 10000000, message = "{bracket.price.illegal}")
	private Integer price = 0;

	@NotNull(message = "{bracket.remark.null}")
	@Length(min = 0, max = 150, message = "{bracket.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = 0;

	public Bracket() {
	}

	public Bracket(String id) {
		this.id = id;
	}

	public Bracket(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {

		return "[ id:" + this.id + " plateNum:" + this.plateNum + " name:"
				+ this.plateNum + " name:" + this.name + " size:" + this.size
				+ " buyDate :" + this.buyDate + " remark:" + this.remark
				+ " entryDate:" + " status:" + this.status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
