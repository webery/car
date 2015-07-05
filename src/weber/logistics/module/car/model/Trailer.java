package weber.logistics.module.car.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import weber.logistics.module.common.model.Employee;

public class Trailer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id = null;

	@NotEmpty(message = "{trailer.plateNum.null}")
	@Length(min = 7, max = 7, message = "{trailer.plateNum.length.illegal}")
	@Pattern(regexp = "^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$", message = "{trailer.code.illegal}")
	private String plateNum = null;

	@NotEmpty(message = "{trailer.name.null}")
	@Length(min = 1, max = 15, message = "{trailer.name.length.illegal}")
	private String name = null;

	@NotNull(message = "{trailer.buyDate.null}")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date buyDate = null;

	@NotNull(message = "{trailer.price.null}")
	@Range(min = 0, max = 10000000, message = "{trailer.price.illegal}")
	private Integer price = 0;

	private Employee employee = null;
	private Container container = null;
	private Bracket bracket = null;

	@NotNull(message = "{trailer.remark.null}")
	@Length(min = 0, max = 150, message = "{trailer.remark.length.illegal}")
	private String remark = null;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate = null;

	private Integer status = 0;

	public Trailer() {
	}

	public Trailer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Trailer(String id, String employee, String container, String bracket) {
		this.setId(id);
		this.setEmployee(new Employee(employee));
		this.setContainer(new Container(container));
		this.setBracket(new Bracket(bracket));
	}

	@Override
	public String toString() {

		return "[ id:" + this.id + " name:" + this.name + " plateNum:"
				+ this.plateNum + " buyDate:" + this.buyDate + " price:"
				+ this.price + " employee:" + this.employee + " container:"
				+ this.container + " bracket:" + this.bracket + " remark:"
				+ this.remark + " entryDate:" + this.entryDate + " status:"
				+ this.status + "]";
	}

	public Trailer(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	//

}
