package weber.logistics.module.car.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import weber.logistics.module.common.model.City;

public class Route implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotNull(message = "{route.start.null}")
	private City start = null;

	@NotNull(message = "{route.destination.null}")
	private City destination = null;

	private String name = null;

	public Route() {
	}

	public Route(City start, City destination) {
		this.start = start;
		this.destination = destination;
	}

	public Route(City start, City destination, String name) {
		this.start = start;
		this.destination = destination;
		this.name = name;
	}

	public Route(City start, City destination, String id, String name) {
		this.start = start;
		this.destination = destination;
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " start:" + this.start + " destination:"
				+ this.destination + " name:" + this.name + "]";
	}

	public City getStart() {
		return start;
	}

	public void setStart(City start) {
		this.start = start;
	}

	public City getDestination() {
		return destination;
	}

	public void setDestination(City destination) {
		this.destination = destination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
