package weber.logistics.module.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import weber.logistics.module.car.model.Trip;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{employee.id.null}")
	@Length(min = 5, max = 10, message = "{employee.id.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{5,10}", message = "{employee.id.illegal}")
	private String id = null;

	@NotEmpty(message = "{employee.name.null}")
	@Length(min = 1, max = 15, message = "{employee.name.length.illegal}")
	private String name = null;

	@NotEmpty(message = "{employee.gender.null}")
	@Length(min = 1, max = 1, message = "{employee.gender.illegal}")
	private String gender = null;

	@NotNull(message = "{employee.birth.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth = null;

	@NotNull(message = "{employee.nation.null}")
	private Nation nation = null;

	@Length(min = 6, max = 15, message = "{employee.phone.length.illegal}")
	@Pattern(regexp = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}", message = "{employee.phone.illegal}")
	private String phone = null;

	@NotNull(message = "{employee.province.null}")
	private Province province = null;

	@NotNull(message = "{employee.address.null}")
	@Length(min = 0, max = 25, message = "{employee.address.length.illegal}")
	private String address = null;

	@NotEmpty(message = "{employee.position.null}")
	@Length(min = 1, max = 15, message = "{employee.position.length.illegal}")
	private String position = null;

	@NotNull(message = "{employee.province.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate = null;// 入职时间

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	@NotNull(message = "{employee.remark.null}")
	@Length(min = 0, max = 150, message = "{employee.remark.length.illegal}")
	private String remark = null;

	private Integer status = 0;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveDate = null;

	private String password = null;
	private String salt = null;

	//
	private List<Trip> trips = null;

	//

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Employee() {
	}

	public Employee(String id) {
		this.id = id;
	}

	public Employee(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Employee(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	public String toString() {

		return "[id:" + this.id + " name:" + this.name + " gender:"
				+ this.gender + " birth:" + this.birth + " nation:"
				+ this.nation + " phone:" + this.phone + " province:"
				+ this.province + " address:" + this.address + " position:"
				+ this.position + " entryDate:" + this.entryDate + " remark:"
				+ this.remark + " password:" + this.password + " salt:"
				+ this.salt + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
