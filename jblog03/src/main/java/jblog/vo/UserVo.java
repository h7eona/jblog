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
}
