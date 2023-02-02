package com.example.enchere.ModelAdmin.Vue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.enchere.Base.Connexion;
import com.example.enchere.ModelAdmin.Rechargement;

public class V_Utilisateur_Rechargement extends Rechargement{
	private String nom;
	private String prenom;
	private String email;
	private float solde_compte;
	
	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public float getSolde_compte() {
		return solde_compte;
	}


	public void setSolde_compte(float solde_compte) {
		this.solde_compte = solde_compte;
	}


	public ArrayList<V_Utilisateur_Rechargement> selectAll() throws Exception
	{
		String requete = "select * from v_utilisateur_rechargement";
		Connection connex = null;
		Statement state = null;
		ArrayList<V_Utilisateur_Rechargement> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				V_Utilisateur_Rechargement get = new V_Utilisateur_Rechargement();
				get.setIdutilisateur(rs.getInt("idutilisateur"));
				get.setNom(rs.getString("nom"));
				get.setPrenom(rs.getString("prenom"));
				get.setEmail(rs.getString("email"));
				get.setSolde_compte(rs.getFloat("solde_compte"));
				get.setMontantrecharge(rs.getFloat("montantrecharge"));
				get.setValidation(rs.getInt("validation"));
				liste.add(get);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(state != null)
			{
				
				state.close();
			}
			if(connex != null)
			{
				connex.close();
			}
		}
		return liste;
	}
	
	public ArrayList<V_Utilisateur_Rechargement> selectById(int id) throws Exception
	{
		String requete = "select * from v_utilisateur_rechargement where idutilisateur='"+id+"'";
		Connection connex = null;
		Statement state = null;
		ArrayList<V_Utilisateur_Rechargement> liste = new ArrayList<V_Utilisateur_Rechargement>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				V_Utilisateur_Rechargement get = new V_Utilisateur_Rechargement();
				get.setIdutilisateur(rs.getInt("idutilisateur"));
				get.setNom(rs.getString("nom"));
				get.setPrenom(rs.getString("prenom"));
				get.setEmail(rs.getString("email"));
				get.setSolde_compte(rs.getFloat("solde_compte"));
				get.setMontantrecharge(rs.getFloat("montantrecharge"));
				get.setValidation(rs.getInt("validation"));
				liste.add(get);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(state != null)
			{
				
				state.close();
			}
			if(connex != null)
			{
				connex.close();
			}
		}
		return liste;
	}
	
	
	public ArrayList<V_Utilisateur_Rechargement> select_non_valide() throws Exception
	{
		String requete = "select * from rechargement_non_valide";
		Connection connex = null;
		Statement state = null;
		ArrayList<V_Utilisateur_Rechargement> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				V_Utilisateur_Rechargement get = new V_Utilisateur_Rechargement();
				get.setIdutilisateur(rs.getInt("idutilisateur"));
				get.setNom(rs.getString("nom"));
				get.setPrenom(rs.getString("prenom"));
				get.setEmail(rs.getString("email"));
				get.setSolde_compte(rs.getFloat("solde_compte"));
				get.setMontantrecharge(rs.getFloat("montantrecharge"));
				get.setValidation(rs.getInt("validation"));
				liste.add(get);
			}
		}
		catch(Exception e)
		{
			throw e;
		}
		finally
		{
			if(state != null)
			{
				
				state.close();
			}
			if(connex != null)
			{
				connex.close();
			}
		}
		return liste;
	}
	
	
}
