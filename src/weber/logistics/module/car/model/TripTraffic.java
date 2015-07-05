package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class TripTraffic implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id = null;

	@NotEmpty(message = "{tripTraffic.name.null}")
	@Length(min = 1, max = 30, message = "{tripTraffic.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{tripTraffic.fine.null}")
	@Range(min = 0, max = 10000000, message = "{tripTraffic.fine.illegal}")
	private Integer fine = 0;

	@NotNull(message = "{tripTraffic.point.null}")
	@Range(min = 0, max = 50, message = "{tripTraffic.point.illegal}")
	private Integer point = 0;

	@NotNull(message = "{tripTraffic.address.null}")
	@Length(min = 0, max = 30, message = "{tripTraffic.address.length.illegal}")
	private String address = null;

	@NotNull(message = "{tripTraffic.remark.null}")
	@Length(min = 0, max = 150, message = "{tripTraffic.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = null;

	//
	private Trip trip = null;

	public TripTraffic() {
	}

	public TripTraffic(Long id) {
		this.id = id;
	}

	public TripTraffic(Long id, Integer status) {
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " name:" + this.name + " fine:" + this.fine
				+ " point:" + this.point + " address:" + this.address
				+ " remark:" + this.remark + " entryDate:" + this.entryDate
				+ " status:" + this.status + "]";
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

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
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

}
