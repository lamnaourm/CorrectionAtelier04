package com.produits.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.utils.HibernateUtils;
import com.produits.models.Produit;

public class DaoProduit implements IDao<Produit> {

	@Override
	public List<Produit> getAll() {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		List<Produit> prds = s.createQuery("from Produit").list();
		t.commit();
		s.close();
		return prds;
	}

	@Override
	public Produit getOne(int id) {
		Session s = HibernateUtils.getSessionFactory().getCurrentSession();
		Transaction t = s.beginTransaction();
		return s.get(Produit.class, id);
	}

	@Override
	public boolean save(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
