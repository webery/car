package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import weber.logistics.module.common.model.Employee;

public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id = null;

	@NotNull(message = "{trip.employee.null}")
	private Employee employee = null;

	@NotNull(message = "{trip.trailer.null}")
	private Trailer trailer = null;

	@NotNull(message = "{trip.container.null}")
	private Container container = null;

	@NotNull(message = "{trip.bracket.null}")
	private Bracket bracket = null;

	@NotNull(message = "{trip.oilcard.null}")
	private OilCard oilcard = null;
	//

	@NotNull(message = "{trip.profit.null}")
	@Range(min = 0, max = 10000000, message = "{trip.profit.illegal}")
	private Integer profit = 0;// 利润

	@NotNull(message = "{trip.earning.null}")
	@Range(min = 0, max = 10000000, message = "{trip.earning.illegal}")
	private Integer earning = 0;// 总货款

	@NotNull(message = "{trip.payment.null}")
	@Range(min = 0, max = 10000000, message = "{trip.payment.illegal}")
	private Integer payment = 0;// 客户实际总支付的钱

	@NotNull(message = "trip.salary.null")
	@Range(min = 0, max = 10000000, message = "{trip.salary.illegal}")
	private Integer salary = 0;

	@NotNull(message = "trip.cash.null")
	@Range(min = 0, max = 10000000, message = "{trip.cash.illegal}")
	private Integer cash = 0;

	@NotNull(message = "trip.roadToll.null")
	@Range(min = 0, max = 10000000, message = "{trip.roadToll.illegal}")
	private Integer roadToll = 0;//

	@NotNull(message = "trip.oilBalance.null")
	@Range(min = 0, max = 10000000, message = "{trip.oilBalance.illegal}")
	private Integer oilBalance = 0;// 下单油卡余额

	@NotNull(message = "trip.oilPayment.null")
	@Range(min = 0, max = 10000000, message = "{trip.oilPayment.illegal}")
	private Integer oilPayment = 0;// 本次单油卡总共充值

	@NotNull(message = "trip.oilMoney.null")
	@Range(min = 0, max = 10000000, message = "{trip.oilMoney.illegal}")
	private Integer oilMoney = 0;// 油耗

	@NotNull(message = "trip.maintenanceCosts.null")
	@Range(min = 0, max = 10000000, message = "{trip.maintenanceCosts.illegal}")
	private Integer maintenanceCosts = 0;// 维修费用

	@NotNull(message = "trip.trafficTicket.null")
	@Range(min = 0, max = 10000000, message = "{trip.trafficTicket.illegal}")
	private Integer trafficTicket = 0;// 违章罚款

	@NotNull(message = "trip.allowance.null")
	@Range(min = 0, max = 10000000, message = "{trip.allowance.illegal}")
	private Integer allowance = 0;// 补助,住房,餐补等

	@NotNull(message = "trip.deductMoney.null")
	@Range(min = 0, max = 10000000, message = "{trip.deductMoney.illegal}")
	private Integer deductMoney = 0;// 扣钱

	@NotNull(message = "trip.reward.null")
	@Range(min = 0, max = 10000000, message = "{trip.reward.illegal}")
	private Integer reward = 0;// 奖励

	@NotNull(message = "{trip.startDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate = null;

	@NotNull(message = "{trip.finishDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date finishDate = null;

	@NotNull(message = "{trip.remark.null}")
	@Length(min = 0, max = 150, message = "{trip.remark.length.illegal}")
	private String remark = null;

	private Integer isPay = 0;// 是否已经支付货款
	private Integer salaryPay = 0;// 是否已经支付工资

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = null;

	//
	private List<TripRoute> tripRoutes = null;
	private TripMaintenance tripMaintenance = null;
	private TripTraffic tripTraffic = null;

	public Trip() {
	}

	public Trip(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "[ id:" + this.id + " employee:" + this.employee + " trailer:"
				+ this.trailer + " container:" + this.container + " bracket:"
				+ this.bracket + " oilcard:" + this.oilcard + " profit:"
				+ this.profit + " earning:" + this.earning + " payment:"
				+ this.payment + " salary:" + this.salary + " cash:"
				+ this.cash + " roadToll:" + this.roadToll + " oilBalance:"
				+ this.oilBalance + " oilPayment:" + this.oilPayment
				+ " oilMoney:" + this.oilMoney + " maintenanceCosts:"
				+ this.maintenanceCosts + " trafficTicket:"
				+ this.trafficTicket + " allowance:" + this.allowance
				+ " deductMoney:" + this.deductMoney + " reward:" + this.reward
				+ " startDate:" + this.startDate + " finishDate:"
				+ this.finishDate + " remark:" + this.remark + " isPay:"
				+ this.isPay + " salaryPay:" + this.salaryPay + " entryDate:"
				+ this.entryDate + " status:" + this.status + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Trailer getTrailer() {
		return trailer;
	}

	public void setTrailer(Trailer trailer) {
		this.trailer = trailer;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public Bracket getBracket() {
		return bracket;
	}

	public void setBracket(Bracket bracket) {
		this.bracket = bracket;
	}

	public OilCard getOilcard() {
		return oilcard;
	}

	public void setOilcard(OilCard oilcard) {
		this.oilcard = oilcard;
	}

	public Integer getProfit() {
		return profit;
	}

	public void setProfit(Integer profit) {
		this.profit = profit;
	}

	public Integer getEarning() {
		return earning;
	}

	public void setEarning(Integer earning) {
		this.earning = earning;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getCash() {
		return cash;
	}

	public void setCash(Integer cash) {
		this.cash = cash;
	}

	public Integer getRoadToll() {
		return roadToll;
	}

	public void setRoadToll(Integer roadToll) {
		this.roadToll = roadToll;
	}

	public Integer getOilBalance() {
		return oilBalance;
	}

	public void setOilBalance(Integer oilBalance) {
		this.oilBalance = oilBalance;
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

	public Integer getMaintenanceCosts() {
		return maintenanceCosts;
	}

	public void setMaintenanceCosts(Integer maintenanceCosts) {
		this.maintenanceCosts = maintenanceCosts;
	}

	public Integer getTrafficTicket() {
		return trafficTicket;
	}

	public void setTrafficTicket(Integer trafficTicket) {
		this.trafficTicket = trafficTicket;
	}

	public Integer getAllowance() {
		return allowance;
	}

	public void setAllowance(Integer allowance) {
		this.allowance = allowance;
	}

	public Integer getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(Integer deductMoney) {
		this.deductMoney = deductMoney;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
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

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
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

	public List<TripRoute> getTripRoutes() {
		return tripRoutes;
	}

	public void setTripRoutes(List<TripRoute> tripRoutes) {
		this.tripRoutes = tripRoutes;
	}

	public TripMaintenance getTripMaintenance() {
		return tripMaintenance;
	}

	public void setTripMaintenance(TripMaintenance tripMaintenance) {
		this.tripMaintenance = tripMaintenance;
		this.maintenanceCosts = tripMaintenance != null ? tripMaintenance
				.getMoney() : 0;
	}

	public TripTraffic getTripTraffic() {
		return tripTraffic;
	}

	public void setTripTraffic(TripTraffic tripTraffic) {
		this.tripTraffic = tripTraffic;
		this.trafficTicket = tripTraffic != null ? tripTraffic.getFine() : 0;
	}

	public Integer getSalaryPay() {
		return salaryPay;
	}

	public void setSalaryPay(Integer salaryPay) {
		this.salaryPay = salaryPay;
	}

	//
}
