package com.contact.management.system.repository;

import com.contact.management.system.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
