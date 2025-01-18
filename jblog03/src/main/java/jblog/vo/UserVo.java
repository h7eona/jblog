package jblog.vo;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;

public class UserVo {
	@NotEmpty
	@Length(min=4, max=50)
	private String id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Length(min=4, max=16)
	private String password;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
