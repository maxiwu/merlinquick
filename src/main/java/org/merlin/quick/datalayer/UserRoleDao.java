package org.merlin.quick.datalayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.merlin.quick.model.User;
import org.merlin.quick.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRoleDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void createUserRole(UserRole userRole){
		//userRole.getUser().setPassword(bCryptPasswordEncoder.encode(userRole.getUser().getPassword()));
		entityManager.persist(userRole);
	}
	
	public void deleteUserRole(UserRole userRole){
		entityManager.remove(userRole);
	}
	
	public List<UserRole> getAll() {
		return entityManager.createQuery("from UserRole", UserRole.class).getResultList();
	}
	
	public List<UserRole> getByRole(String role) {
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRole> query = cb.createQuery(UserRole.class);		
		Root e = query.from(UserRole.class);
		query.where(cb.equal(e.get("role"), role));
		List<UserRole> result = entityManager.createQuery(query).getResultList();
		
		return result;
	}
	
	public List<UserRole> findByUser(User user)
	{
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<UserRole> query = cb.createQuery(UserRole.class);		
		Root e = query.from(UserRole.class);
		query.where(cb.equal(e.get("user"), user));
		List<UserRole> result = entityManager.createQuery(query).getResultList();
		return result;
	}


}