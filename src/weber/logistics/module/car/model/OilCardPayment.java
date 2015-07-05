package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class OilCardPayment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;

	// @NotNull(message = "{oilCardPayment.oilCard.null}")
	private OilCard oilCard = null;

	// @NotNull(message = "{oilCardPayment.trip.null}")
	private Trip trip = null;

	@NotNull(message = "{oilCardPayment.tripRoute.null}")
	private TripRoute tripRoute = null;// 根据查询triproue设置其他参数

	private Route route = null;

	@NotNull(message = "oilCardPayment.client.null}")
	private Client client = null;

	@NotNull(message = "{oilCardPayment.money.null}")
	@Range(min = 0, max = 10000000, message = "{oilCardPayment.money.illegal}")
	private Integer money = 0;

	@NotNull(message = "{oilCardPayment.balance.null}")
	@Range(min = 0, max = 10000000, message = "{oilCardPayment.balance.illegal}")
	private Integer balance = 0;

	@NotNull(message = "{oilCardPayment.payDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate = null;

	@NotNull(message = "{oilCardPayment.remark.null}")
	@Length(min = 0, max = 150, message = "{oilCardPayment.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = 0;

	public OilCardPayment() {
	}

	public OilCardPayment(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[ id:" + this.id + " oilCard:" + this.oilCard + " trip:"
				+ this.trip + " tripRoute:" + this.trip + " route:"
				+ this.route + " client:" + this.client + " money:"
				+ this.money + " balance:" + this.balance + " payDate:"
				+ this.payDate + " remark:" + this.remark + " entryDate:"
				+ this.entryDate + " status:" + this.status + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
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

	public TripRoute getTripRoute() {
		return tripRoute;
	}

	public void setTripRoute(TripRoute tripRoute) {
		this.tripRoute = tripRoute;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public OilCard getOilCard() {
		return oilCard;
	}

	public void setOilCard(OilCard oilCard) {
		this.oilCard = oilCard;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}
