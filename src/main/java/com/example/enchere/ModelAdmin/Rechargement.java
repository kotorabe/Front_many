package com.example.enchere.ModelAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

import com.example.enchere.Base.Connexion;
import com.example.enchere.ModelClient.Utilisateur;

public class
Rechargement {
	private int idrechargement;
	private int idutilisateur;
	private float montantrecharge;
	private LocalDateTime dateheurechargement;
	private int validation;

	public int getIdrechargement() {
		return idrechargement;
	}
	public void setIdrechargement(int idrechargement) {
		this.idrechargement = idrechargement;
	}

	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
	public float getMontantrecharge() {
		return montantrecharge;
	}
	public void setMontantrecharge(float montantrecharge) {
		this.montantrecharge = montantrecharge;
	}
	public void setMontantrecharge(String montant)
	{
		this.montantrecharge = Float.parseFloat(montant);
	}
	
	public LocalDateTime getDateheurechargement() {
		return dateheurechargement;
	}
	public void setDateheurechargement(LocalDateTime dateheurechargement) {
		this.dateheurechargement = dateheurechargement;
	}
	public int getValidation() {
		return validation;
	}
	public void setValidation(int validation) {
		this.validation = validation;
	}
	
	public boolean create(Rechargement recharge) throws Exception
	{
		boolean retour = false;
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			String requete = "insert into rechargement values(default,'"+recharge.getIdutilisateur()+"','"+recharge.getMontantrecharge()+"',default,'0')";
			state.execute(requete);
			retour = true;
		}
		catch(Exception e)
		{
			throw new Exception("L'insertion du rechargement est impossible");
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
		return retour;
	}
	public Rechargement selectByIdRecharge(int idrechargement) throws Exception
	{
		String requete = "select * from rechargement where idrechargement='"+idrechargement+"'";
		
		Connection connex = null;
		Statement state = null;
		Rechargement retour = new Rechargement();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				retour.setIdutilisateur(rs.getInt("idutilisateur"));
				retour.setMontantrecharge(rs.getFloat("montantrecharge"));
				
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
		return retour;
	}
	public boolean validation_insertion(int idrechargement) throws Exception
	{
		boolean retour = false;
		Rechargement recharge = new Rechargement().selectByIdRecharge(idrechargement);
		Utilisateur user = new Utilisateur().selectById(recharge.getIdutilisateur());
		Connection connex = null;
		try
		{
			connex = new Connexion().setConnect();
			connex.setAutoCommit(false);
			float total = recharge.getMontantrecharge()+user.getSolde_compte();
			String requete1 = "update utilisateur set solde_compte='"+total+"' where idutilisateur='"+recharge.getIdutilisateur()+"'";
			String requete2="update rechargement set validation=1 , dateheurechargement=now() where idrechargement='"+idrechargement+"'";
			PreparedStatement stat1 = connex.prepareStatement(requete1);
			PreparedStatement stat2 = connex.prepareStatement(requete2);
			stat1.executeUpdate();
			stat2.executeUpdate();
			connex.commit();
			retour = true;
		}
		catch(Exception e)
		{
			if(connex != null)
			{
				connex.rollback();
			}
			throw e;
		}
		finally
		{
			if(connex != null)
			{
				connex.close();
			}
		}
		return retour;
		
	}
	
}
