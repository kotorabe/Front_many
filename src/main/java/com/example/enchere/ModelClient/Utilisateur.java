package com.example.enchere.ModelClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.enchere.Base.Connexion;
import com.example.enchere.Util.Token;

public class Utilisateur {
	private int idutiilisateur;
	private String nom;
	private String prenom;
	private String email;
	private String mdp;
	private float solde_compte;
	public int getIdutiilisateur() {
		return idutiilisateur;
	}
	public void setIdutiilisateur(int idutiilisateur) {
		this.idutiilisateur = idutiilisateur;
	}
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
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public float getSolde_compte() {
		return solde_compte;
	}
	public void setSolde_compte(float solde_compte) {
		this.solde_compte = solde_compte;
	}
	
	public int getIdUtilisateur(Utilisateur user) throws Exception
	{
		String requete = "select * from utilisateur where email='"+user.getEmail()+"' and mdp='"+user.getMdp()+"'";
		Connection connex = null;
		Statement state = null;
		Utilisateur utilisateur = new Utilisateur();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			int nbr = 0;
			while(rs.next())
			{
				utilisateur.setIdutiilisateur(rs.getInt("idutilisateur"));
				nbr++;
			}
			if(nbr == 0)
			{
				throw new Exception("L'utilisateur n'existe pas");
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
		return utilisateur.getIdutiilisateur();
	}
	
	public Token login(Utilisateur user) throws Exception
	{
		Token token = new Token();
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			user.setMdp(Token.toAsh(user.getMdp()));
			int id = new Utilisateur().getIdUtilisateur(user);
			String requete2 = "select * from token where idutilisateur='"+id+"'";
			ResultSet rs = state.executeQuery(requete2);
			while(rs.next())
			{
				token.setExpire(rs.getDate("expire"));
				token.setIdutilisateur(rs.getInt("idutilisateur"));
				token.setToken(rs.getString("token"));
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
		return token;
	}

	public Token loginTok(Utilisateur user) throws Exception
	{
		Token token = new Token();
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			user.setMdp(Token.toAsh(user.getMdp()));
			int id = new Utilisateur().getIdUtilisateur(user);
			String requete2 = "select * from token where idutilisateur='"+id+"'";
			ResultSet rs = state.executeQuery(requete2);
			while(rs.next())
			{
				token.setExpire(rs.getDate("expire"));
				token.setIdutilisateur(rs.getInt("idutilisateur"));
				token.setToken(rs.getString("token"));
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
		return token;
	}
	
	public Utilisateur selectById(int idutiilsateur) throws Exception
	{
		Utilisateur retour = new Utilisateur();
		String requete = "select * from utilisateur where idutilisateur='"+idutiilsateur+"'";
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				retour.setSolde_compte(rs.getFloat("solde_compte"));
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
	
	public boolean inscription(Utilisateur utilisateur) throws Exception
	{
		utilisateur.setMdp(Token.toAsh(utilisateur.getMdp()));
		String requete = "insert into utilisateur values(default,'"+utilisateur.getNom()+"','"+utilisateur.getPrenom()+"','"+utilisateur.getEmail()+"','"+utilisateur.getMdp()+"','0')";
		boolean retour = false;
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			state.execute(requete);
			int user = new Utilisateur().getIdUtilisateur(utilisateur);
			Token token = new Token();
			String requete2 = "insert into token values('"+token.tokengenerator(utilisateur.getEmail(), utilisateur.getMdp())+"','"+token.getExpire()+"','"+user+"')";		
			state.execute(requete2);
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
	
	public float solde(String token) throws Exception
	{
		float retour = 0;
		String requete = "select * from v_utilisateur_token where token='"+token+"'";
		Connection connex = null;
		Statement state = null;
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				retour = rs.getFloat("solde_compte");
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
