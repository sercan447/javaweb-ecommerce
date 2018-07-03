package com.muzikmagaza.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.muzikmagaza.dao.CartDao;
import com.muzikmagaza.model.Cart;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Cart getCartById(int cartId) {
		Session session = sessionFactory.getCurrentSession();
		return (Cart)session.get(Cart.class, cartId);
	}

	@Override
	public void update(Cart cart) {
		int cartId = cart.getCartId();		
	}

}
