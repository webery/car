package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

public class TripRoute implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id = null;
	@NotNull(message = "{tripRoute.trip.null}")
	private Trip trip = null;

	@NotNull(message = "{tripRoute.route.null}")
	private Route route = null;

	@NotNull(message = "{tripRoute.client.null}")
	private Client client = null;

	@NotNull(message = "{tripRoute.earning.null}")
	@Range(min = 0, max = 10000000, message = "{trip.earning.illegal}")
	private Integer earning = 0;// 货款

	@NotNull(message = "{tripRoute.payment.null}")
	@Range(min = 0, max = 10000000, message = "{trip.payment.illegal}")
	private Integer payment = 0;// 实际支付

	@NotNull(message = "{tripRoute.arrearage.null}")
	@Range(min = 0, max = 10000000, message = "{trip.arrearage.illegal}")
	private Integer arrearage = 0;// 欠款

	@NotNull(message = "{tripRoute.salary.null}")
	@Range(min = 0, max = 10000000, message = "{trip.salary.illegal}")
	private Integer salary = 0;// 工资

	@NotNull(message = "{tripRoute.oilPayment.null}")
	@Range(min = 0, max = 10000000, message = "{trip.oilPayment.illegal}")
	private Integer oilPayment = 0;// 本次路线充值

	@NotNull(message = "{tripRoute.oilMoney.null}")
	@Range(min = 0, max = 10000000, message = "{trip.oilMoney.illegal}")
	private Integer oilMoney = 0;// 本次总油耗

	@NotNull(message = "{tripRoute.startDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate = null;

	@NotNull(message = "{tripRoute.finishDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finishDate = null;

	@NotNull(message = "{tripRoute.remark.null}")
	@Length(min = 0, max = 150, message = "{tripRoute.remark.length.illegal}")
	private String remark = null;

	private Integer isPay = 0;// 是否已经支付工资

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = null;

	//
	private Trailer trailer = null;

	public TripRoute() {
	}

	public TripRoute(String id) {
		this.id = id;
	}

	public TripRoute(String id, Integer status) {
		this.id = id;
		this.status = status;
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " trip:" + this.trip + " route:"
				+ this.route + " client:" + this.client + " earning:"
				+ this.earning + " payment:" + this.payment + " arrearage:"
				+ this.arrearage + " salary:" + this.salary + " oilPayment:"
				+ this.oilPayment + " oilMoney:" + this.oilMoney
				+ " startDate:" + this.startDate + " finishDate:"
				+ this.finishDate + " remark:" + this.remark + " entryDate:"
				+ this.entryDate + " isPay:" + this.isPay + " status:"
				+ this.status + " ]";
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Integer getEarning() {
		return earning;
	}

	public void setEarning(Integer earning) {
		this.earning = earning;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getArrearage() {
		return arrearage;
	}

	public void setArrearage(Integer arrearage) {
		this.arrearage = arrearage;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getOilPayment() {
		return oilPayment;
	}

	public void setOilPayment(Integer oilPayment) {
		this.oilPayment = oilPayment;
	}

	public Integer getOilMoney() {
		return oilMoney;
	}

	public void setOilMoney(Integer oilMoney) {
		this.oilMoney = oilMoney;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

}
