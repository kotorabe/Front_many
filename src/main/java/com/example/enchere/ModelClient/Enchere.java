package com.example.enchere.ModelClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.enchere.Base.Connexion;
import com.example.enchere.ModelAdmin.Categorie;

public class Enchere {
	private int idenchere;
	private int idutilisateur;
	private double dureeenchere;
	private String description;
	private int idcategorie;
	private LocalDateTime dateheureenchere;
	private LocalDateTime datefinenchere;
	private float prixdevente;
	private float prixminimum;
	private int etat;

	private String categorie;

	public int getIdenchere() {
		return idenchere;
	}
	public void setIdenchere(int idenchere) {
		this.idenchere = idenchere;
	}
	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
	public double getDureeenchere() {
		return dureeenchere;
	}
	public void setDureeenchere(double dureeenchere) {
		this.dureeenchere = dureeenchere;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdcategorie() {
		return idcategorie;
	}
	public void setIdcategorie(int idcategorie) {
		this.idcategorie = idcategorie;
	}
	
	public LocalDateTime getDateheureenchere() {
		return dateheureenchere;
	}
	public void setDateheureenchere(LocalDateTime dateheureenchere) {
		this.dateheureenchere = dateheureenchere;
	}
	public float getPrixdevente() {
		return prixdevente;
	}
	public void setPrixdevente(float prixdevente) {
		this.prixdevente = prixdevente;
	}
	public float getPrixminimum() {
		return prixminimum;
	}
	public void setPrixminimum(float prixminimum) {
		this.prixminimum = prixminimum;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}

	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public LocalDateTime getDatefinenchere() {
		return datefinenchere;
	}
	public void setDatefinenchere(LocalDateTime datefinenchere) {
		this.datefinenchere = datefinenchere;
	}
	public ArrayList<Enchere> selection(String condition) throws Exception
	{
		String requete = "select * from "+condition;
		Connection connex = null;
		Statement state = null;
		ArrayList<Enchere> liste = new ArrayList<>();
		try 
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			 ResultSet rs = state.executeQuery(requete);
			 while(rs.next())
			 {
				 Enchere enchere = new Enchere();
				 enchere.setIdenchere(rs.getInt("idenchere"));
				 enchere.setIdutilisateur(rs.getInt("idutilisateur"));
				 enchere.setIdcategorie(rs.getInt("idcategorie"));
				 enchere.setDureeenchere(rs.getDouble("dureeenchere"));
				 enchere.setDescription(rs.getString("description"));
				 enchere.setDateheureenchere(rs.getTimestamp("dateheureenchere").toLocalDateTime());
				 enchere.setPrixdevente(rs.getFloat("prixdevente"));
				 enchere.setPrixminimum(rs.getFloat("prixminimum"));
				 enchere.setDatefinenchere(rs.getTimestamp("datefinenchere").toLocalDateTime());
				 enchere.setEtat(rs.getInt("etat"));
				 liste.add(enchere);
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
	public ArrayList<Enchere> List() throws Exception
	{
		String requete = "select * from  v_enchere_categorie";
		Connection connex = null;
		Statement state = null;
		ArrayList<Enchere> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Enchere enchere = new Enchere();
				enchere.setIdenchere(rs.getInt("idenchere"));
				enchere.setIdutilisateur(rs.getInt("idutilisateur"));
				enchere.setIdcategorie(rs.getInt("idcategorie"));
				enchere.setDureeenchere(rs.getDouble("dureeenchere"));
				enchere.setDescription(rs.getString("description"));
				enchere.setDateheureenchere(rs.getTimestamp("dateheureenchere").toLocalDateTime());
				enchere.setPrixdevente(rs.getFloat("prixdevente"));
				enchere.setPrixminimum(rs.getFloat("prixminimum"));
				enchere.setDatefinenchere(rs.getTimestamp("datefinenchere").toLocalDateTime());
				enchere.setEtat(rs.getInt("etat"));
				enchere.setCategorie(rs.getString("categorie"));
				liste.add(enchere);
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
	public ArrayList<Enchere> selectById(int id) throws Exception
	{
		String requete = "select * from  v_enchere_categorie where idenchere = '"+id+"'";
		Connection connex = null;
		Statement state = null;
		ArrayList<Enchere> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Enchere enchere = new Enchere();
				enchere.setIdenchere(rs.getInt("idenchere"));
				enchere.setIdutilisateur(rs.getInt("idutilisateur"));
				enchere.setIdcategorie(rs.getInt("idcategorie"));
				enchere.setDureeenchere(rs.getDouble("dureeenchere"));
				enchere.setDescription(rs.getString("description"));
				enchere.setDateheureenchere(rs.getTimestamp("dateheureenchere").toLocalDateTime());
				enchere.setPrixdevente(rs.getFloat("prixdevente"));
				enchere.setPrixminimum(rs.getFloat("prixminimum"));
				enchere.setDatefinenchere(rs.getTimestamp("datefinenchere").toLocalDateTime());
				enchere.setEtat(rs.getInt("etat"));
				enchere.setCategorie(rs.getString("categorie"));
				liste.add(enchere);
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

	public ArrayList<Enchere> getNotId(int id) throws Exception
	{
		String requete = "select * from  v_enchere_categorie where idutilisateur != '"+id+"'";
		Connection connex = null;
		Statement state = null;
		ArrayList<Enchere> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Enchere enchere = new Enchere();
				enchere.setIdenchere(rs.getInt("idenchere"));
				enchere.setIdutilisateur(rs.getInt("idutilisateur"));
				enchere.setIdcategorie(rs.getInt("idcategorie"));
				enchere.setDureeenchere(rs.getDouble("dureeenchere"));
				enchere.setDescription(rs.getString("description"));
				enchere.setDateheureenchere(rs.getTimestamp("dateheureenchere").toLocalDateTime());
				enchere.setPrixdevente(rs.getFloat("prixdevente"));
				enchere.setPrixminimum(rs.getFloat("prixminimum"));
				enchere.setDatefinenchere(rs.getTimestamp("datefinenchere").toLocalDateTime());
				enchere.setEtat(rs.getInt("etat"));
				enchere.setCategorie(rs.getString("categorie"));
				liste.add(enchere);
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
	public ArrayList<Enchere> select_valide() throws Exception
	{
		return selection(" enchere where datefinenchere>now()");
	}
	public ArrayList<Enchere> selectall() throws Exception
	{
		return selection(" enchere");
	}
	public ArrayList<Enchere> selectRecherche(String recherche) throws Exception
	{
		//return selection(" v_enchere_categorie where description like '%"+recherche+"%' or categorie like '%"+recherche+"%'");
		String requete = "select * from  v_enchere_categorie where description like '%"+recherche+"%' or categorie like '%"+recherche+"%'";
		Connection connex = null;
		Statement state = null;
		ArrayList<Enchere> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Enchere enchere = new Enchere();
				enchere.setIdenchere(rs.getInt("idenchere"));
				enchere.setIdutilisateur(rs.getInt("idutilisateur"));
				enchere.setIdcategorie(rs.getInt("idcategorie"));
				enchere.setDureeenchere(rs.getDouble("dureeenchere"));
				enchere.setDescription(rs.getString("description"));
				enchere.setDateheureenchere(rs.getTimestamp("dateheureenchere").toLocalDateTime());
				enchere.setPrixdevente(rs.getFloat("prixdevente"));
				enchere.setPrixminimum(rs.getFloat("prixminimum"));
				enchere.setDatefinenchere(rs.getTimestamp("datefinenchere").toLocalDateTime());
				enchere.setEtat(rs.getInt("etat"));
				enchere.setCategorie(rs.getString("categorie"));
				liste.add(enchere);
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

	public boolean insertion(Enchere enchere) throws Exception
	{
		LocalDateTime now = java.time.LocalDateTime.now();
		Long ajout = new Long((long) enchere.getDureeenchere());
		LocalDateTime fin = now.plusHours(ajout);
		boolean retour = false;
		Categorie categorie = new Categorie().selectById(enchere.getIdcategorie());
		System.out.println(now);
		System.out.println(fin);
		String requete = "insert into enchere values(default,'"+enchere.getIdutilisateur()+"','"+enchere.getDureeenchere()+"','"+enchere.getDescription()+"','"+enchere.getIdcategorie()+"','"+now+"','"+enchere.getPrixdevente()+"','"+enchere.getPrixminimum()+"',0,'"+fin+"')";
		Connection connex = null;
		Statement state = null;
		try
		{
			if(enchere.getDureeenchere()>categorie.getDureeEnchereCategorie())
			{
				return false;
			}
			else
			{
				connex = new Connexion().setConnect();
				state = connex.createStatement();
				state.execute(requete);
				retour = true;
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
}
