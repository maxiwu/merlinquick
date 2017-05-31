package org.merlin.quick.service;

import org.merlin.quick.datalayer.UserDao;
import org.merlin.quick.datalayer.UserRoleDao;
import org.merlin.quick.model.User;
import org.merlin.quick.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailsService {

	@Autowired
	UserDao userDao;
	@Autowired
	UserRoleDao roleDao;
	
	public void createUser(User user, UserRole role)
	{
		userDao.createUser(user);
		roleDao.createUserRole(role);
	}
}
