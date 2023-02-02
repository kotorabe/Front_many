package com.example.enchere.ModelAdmin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.enchere.Base.Connexion;

public class Categorie {
	private int idcategorie;
	private String categorie;
	private double dureeEnchereCategorie;
	public int getIdcategorie() {
		return idcategorie;
	}
	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public double getDureeEnchereCategorie() {
		return dureeEnchereCategorie;
	}
	public void setDureeEnchereCategorie(double dureeEnchereCategorie) {
		this.dureeEnchereCategorie = dureeEnchereCategorie;
	}
	
	public boolean insert(Categorie categorie) throws Exception
	{
		String requete ="insert into categorie values(default,'"+categorie.getCategorie()+"','"+categorie.getDureeEnchereCategorie()+"')";
		Connection connex = null;
		Statement state = null;
		boolean retour = false;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			state.execute(requete);
			retour = true;
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
	
	public ArrayList<Categorie> selectall() throws Exception
	{
		ArrayList<Categorie> liste = new ArrayList<>();
		String requete = "select * from categorie";
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Categorie categorie = new Categorie();
				categorie.setIdcategorie(rs.getInt("idcategorie"));
				categorie.setCategorie(rs.getString("categorie"));
				categorie.setDureeEnchereCategorie(rs.getDouble("dureeencherecategorie"));
				liste.add(categorie);
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
	
	public Categorie selectById(int idcategorie) throws Exception 
	{
		Categorie categorie = new Categorie();
		String requete = "select * from categorie where idcategorie ='"+idcategorie+"'";
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				categorie.setCategorie(rs.getString("categorie"));
				categorie.setDureeEnchereCategorie(rs.getDouble("dureeencherecategorie"));
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
		return categorie;
	}
}
