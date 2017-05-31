package org.merlin.quick.datalayer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.merlin.quick.model.User;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void createUser(User user) {		
		entityManager.persist(user);
	}
}
