package com.example.enchere.ModelAdmin.Vue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.enchere.Base.Connexion;

public class V_Enchere_Solde {
	private int idenchere;
	private float montant;
	private Date dateheureenchere;
	private static float solde = 0;
	public int getIdenchere() {
		return idenchere;
	}
	public void setIdenchere(int idenchere) {
		this.idenchere = idenchere;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public Date getDateheureenchere() {
		return dateheureenchere;
	}
	public void setDateheureenchere(Date dateheureenchere) {
		this.dateheureenchere = dateheureenchere;
	}
	
	public static float getSolde() {
		return solde;
	}
	public ArrayList<V_Enchere_Solde> selectall() throws Exception
	{
		String requete = "select * from enchere_solde";
		Connection connex = null;
		Statement state = null;
		ArrayList<V_Enchere_Solde> liste = new ArrayList<>();
		try
		{	
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				V_Enchere_Solde solde = new V_Enchere_Solde();
				solde.setIdenchere(rs.getInt("idenchere"));
				solde.setMontant(rs.getFloat("montant"));
				V_Enchere_Solde.solde = V_Enchere_Solde.solde + solde.getMontant();
				solde.setDateheureenchere(rs.getDate("dateheureenchere"));
				liste.add(solde);
			}
		}catch(Exception e)
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
