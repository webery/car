package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class OilCard implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotEmpty(message = "{oilCard.code.null}")
	@Length(min = 1, max = 15, message = "{oilCard.code.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{5,10}", message = "{oilCard.code.illegal}")
	private String code = null;

	@NotEmpty(message = "{oilCard.name.null}")
	@Length(min = 1, max = 15, message = "{oilCard.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{oilCard.price.null}")
	@Range(min = 0, max = 10000000, message = "{oilCard.price.illegal}")
	private Integer money = 0;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = 0;

	public OilCard() {
	}

	public OilCard(String id) {
		this.id = id;
	}

	public OilCard(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " code:" + this.code + " name:" + this.name
				+ " money:" + this.money + " enrtyDate:" + this.entryDate
				+ " status:" + this.status + "]";
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

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
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
	
	

}
