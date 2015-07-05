package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class Container implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotEmpty(message = "{container.code.null}")
	@Length(min = 5, max = 10, message = "{container.code.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{5,10}", message = "{container.plateNum.illegal}")
	private String code = null;

	@NotEmpty(message = "{container.name.null}")
	@Length(min = 1, max = 15, message = "{container.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{container.buyDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buyDate = null;

	@NotNull(message = "{container.price.null}")
	@Range(min = 0, max = 10000000, message = "{container.price.illegal}")
	private Integer price = 0;

	@NotNull(message = "{container.size.null}")
	@Range(min = 0, max = 300, message = "{container.size.illegal}")
	private Integer size = 0;

	@NotNull(message = "{container.remark.null}")
	@Length(min = 0, max = 150, message = "{container.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = 0;

	public Container() {
	}

	public Container(String id) {
		this.id = id;
	}

	public Container(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " code:" + this.code + " name:" + this.name
				+ " buyDate:" + this.buyDate + " price:" + this.price
				+ " size:" + this.size + " remark:" + this.remark
				+ " entryDate:" + this.entryDate + " status:" + this.status
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
