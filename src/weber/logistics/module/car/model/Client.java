package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import weber.logistics.module.common.model.City;
import weber.logistics.module.common.model.Province;

public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotEmpty(message = "{client.code.null}")
	@Length(min = 5, max = 10, message = "{client.code.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{5,10}", message = "{client.code.illegal}")
	private String code = null;

	@NotEmpty(message = "{client.name.null}")
	@Length(min = 1, max = 20, message = "{client.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{client.linkman.null}")
	@Length(min = 0, max = 15, message = "{client.linkman.length.illegal}")
	private String linkman = null;// 可以为""但是不能为null

	@NotEmpty(message = "{client.phone.null}")
	@Length(min = 6, max = 15, message = "{client.phone.length.illegal}")
	@Pattern(regexp = "1([\\d]{10})|((\\+[0-9]{2,4})?\\(?[0-9]+\\)?-?)?[0-9]{7,8}", message = "{client.phone.illegal}")
	private String phone = null;

	@NotNull(message = "{client.province.null}")
	private Province province = null;

	@NotNull(message = "{client.city.null}")
	private City city = null;

	@NotNull(message = "{client.address.null}")
	@Length(min = 0, max = 30, message = "{client.address.length.illegal}")
	private String address = null;

	@NotNull(message = "{client.introduce.null}")
	@Length(min = 0, max = 150, message = "{client.introduce.length.illegal}")
	private String introduce = null;// 可以为""但是不能为null

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = null;

	//
	private List<TripRoute> tripRoutes = null;

	public Client() {
	}

	public Client(String id) {
		this.id = id;
	}

	public Client(String id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " code:" + this.code + " name:" + this.name
				+ " linkman:" + this.linkman + " phone:" + this.phone
				+ " province:" + this.province + " city:" + this.city
				+ " address:" + this.address + " introduce:" + this.introduce
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

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public List<TripRoute> getTripRoutes() {
		return tripRoutes;
	}

	public void setTripRoutes(List<TripRoute> tripRoutes) {
		this.tripRoutes = tripRoutes;
	}

}
