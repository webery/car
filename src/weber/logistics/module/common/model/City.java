package weber.logistics.module.common.model;

import java.io.Serializable;

public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private String name = null;
	private Province province = null;

	public City() {
	}
	
	public City(Integer id) {
		this.id = id;
	}

	public City(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {

		return "[id:" + this.getId() + " name:" + this.getName() + " province:"
				+ this.getProvince() + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

}
