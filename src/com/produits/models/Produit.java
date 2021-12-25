package com.produits.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Produit {

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String nom;
	
	@Column(name="prix_achat")
	private double prixAchat;
	
	@Column(name="prix_vente")
	private double prixVente;
	
	
	public Produit() {
		
	}
	
	public Produit( String nom, double prixAchat, double prixVente) {
		this.nom = nom;
		this.prixAchat = prixAchat;
		this.prixVente = prixVente;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}
	public double getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	} 
	
	
	
}
