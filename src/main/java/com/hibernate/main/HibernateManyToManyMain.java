package com.hibernate.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.model.Cart;
import com.hibernate.model.Item;
import com.hibernate.util.HibernateUtil;

public class HibernateManyToManyMain {

	// Saving many-to-many where Cart is primary
	public static void main(String[] args) {

		Item iphone = new Item();
		iphone.setItemPrice(100);
		iphone.setItemDesc("iPhone");

		Item ipod = new Item();
		ipod.setItemPrice(50);
		ipod.setItemDesc("iPod");

		Set<Item> items = new HashSet<Item>();
		items.add(iphone);
		items.add(ipod);

		Cart cart = new Cart();
		cart.setItems(items);
		cart.setCartTotal(150);

		Cart cart1 = new Cart();
		Set<Item> items1 = new HashSet<Item>();
		items1.add(iphone);
		cart1.setItems(items1);
		cart1.setCartTotal(100);

		SessionFactory sessionFactory = null;
		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.getCurrentSession();
			Transaction tx = session.beginTransaction();
			session.save(cart);
			session.save(cart1);
			System.out.println("Before committing transaction");
			tx.commit();
			sessionFactory.close();

			System.out.println("Cart ID=" + cart.getCartId());
			System.out.println("Cart1 ID=" + cart1.getCartId());
			System.out.println("Item1 ID=" + iphone.getItemId());
			System.out.println("Item2 ID=" + ipod.getItemId());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionFactory != null && !sessionFactory.isClosed())
				sessionFactory.close();
		}

	}
}
