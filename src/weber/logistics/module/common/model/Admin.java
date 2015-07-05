package weber.logistics.module.common.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "{admin.account.null}")
	@Length(min = 6, max = 10, message = "{admin.account.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{6,10}", message = "{admin.account.illegal}")
	private String account = null;

	@Length(min = 1, max = 15, message = "{admin.name.length.illegal}")
	private String name = null;

	@NotEmpty(message = "{admin.password.null}")
	@Length(min = 6, max = 10, message = "{admin.password.length.illegal}")
	@Pattern(regexp = "[A-Za-z0-9]{6,10}", message = "{admin.password.illegal}")
	private String password = null;

	private Integer status = 0;

	public Admin() {
	}

	public Admin(String account, String password) {
		this.account = account;
		this.password = password;
	}

	@Override
	public String toString() {
		return "[ account:" + this.account + " name:" + this.name
				+ " password:" + this.password + "]";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
