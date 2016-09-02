package com.project.model;

import com.project.domain.Admin;

public interface AdminModel extends BaseModel<Admin>{
	public Admin login(Admin a);

}
