package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class TripMaintenance implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id = null;

	@NotEmpty(message = "{tripMaintenance.name.null}")
	@Length(min = 1, max = 30, message = "{tripMaintenance.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{tripMaintenance.money.null}")
	@Range(min = 0, max = 10000000, message = "{tripMaintenance.money.illegal}")
	private Integer money = 0;

	@NotNull(message = "{tripMaintenance.address.null}")
	@Length(min = 0, max = 30, message = "{tripMaintenance.address.length.illegal}")
	private String address = null;

	@NotNull(message = "{tripMaintenance.remark.null}")
	@Length(min = 0, max = 150, message = "{tripMaintenance.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = null;

	//
	private Trip trip = null;

	public TripMaintenance() {
	}

	public TripMaintenance(Long id) {
		this.id = id;
	}

	public TripMaintenance(Long id, Integer status) {
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {
		return "[ id:" + this.id + " name:" + this.name + " money:"
				+ this.money + " address:" + this.address + " remark:"
				+ this.remark + " entryDate:" + this.entryDate + " status:"
				+ this.status + " trip:" + this.trip + "]";
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

}
