package aca.bloger.domen.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import aca.bloger.domen.User;

public class UserQueries {

public static User findByNickname(EntityManager em, String nickname) {
		
		String q = "select u from User u where u.nickname =:nickname";
		
		TypedQuery<User> querry = em.createQuery(q, User.class);
		querry.setParameter("nickname", nickname);
		
		List<User> list = querry.getResultList();
		
		if(list.isEmpty()) {return null;}
		
		return list.get(0);
	}
	
	public static List<User> getAllUserss(EntityManager em){
		
		String q = "select u from User u";
		
		TypedQuery<User> querry = em.createQuery(q, User.class);
		
		return querry.getResultList();
		
	}
	
	public static User getUserById(EntityManager em, int id) {
		
		User product = em.find(User.class, id);
		
		return product;
	}
}
