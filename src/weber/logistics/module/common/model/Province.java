package weber.logistics.module.common.model;

import java.io.Serializable;

public class Province implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private String name = null;

	public Province() {
	}

	public Province(Integer id) {
		this.id = id;
	}

	public Province(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {

		return "[id:" + this.getId() + " name:" + this.getName() + "]";
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

}
