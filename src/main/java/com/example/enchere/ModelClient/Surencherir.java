package com.example.enchere.ModelClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.example.enchere.Base.Connexion;

public class Surencherir {
	private int surencherir;
	private int idenchere;
	private int idutilisateur;
	private float montant;
	private LocalDateTime dateheuresurenchere;
	public int getSurencherir() {
		return surencherir;
	}
	public void setSurencherir(int surencherir) {
		this.surencherir = surencherir;
	}
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
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public LocalDateTime getDateheuresurenchere() {
		return dateheuresurenchere;
	}
	public void setDateheuresurenchere(LocalDateTime dateheuresurenchere) {
		this.dateheuresurenchere = dateheuresurenchere;
	}

	public ArrayList<Surencherir> selectById(int id) throws Exception
	{
		String requete = "select * from  surencherir where idutilisateur = '"+id+"'";
		Connection connex = null;
		Statement state = null;
		ArrayList<Surencherir> liste = new ArrayList<>();
		try
		{
			connex = new Connexion().setConnect();
			state = connex.createStatement();
			ResultSet rs = state.executeQuery(requete);
			while(rs.next())
			{
				Surencherir surencherir = new Surencherir();
				surencherir.setSurencherir(rs.getInt("idsurencherir"));
				surencherir.setIdenchere(rs.getInt("idenchere"));
				surencherir.setIdutilisateur(rs.getInt("idutilisateur"));
				surencherir.setMontant(rs.getFloat("montant"));
				surencherir.setDateheuresurenchere(rs.getTimestamp("dateheuresurenchere").toLocalDateTime());
				liste.add(surencherir);
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
	public boolean insertion(Surencherir sur) throws Exception
	{
		boolean retour = false;
		String requete = "insert into surencherir values(default,'"+sur.getIdenchere()+"','"+sur.getIdutilisateur()+"','"+sur.getMontant()+"',default)";
		ArrayList<Enchere> enchere = new Enchere().selectById(sur.idenchere);
		Utilisateur user = new Utilisateur().selectById(sur.getIdutilisateur());
		Connection connex = null;
		try
		{
			if(sur.getIdutilisateur() == enchere.get(0).getIdutilisateur())
			{
				throw new Exception("Vous ne pouvez pas encherir sur votre propre enchere");
			}
			else
			{
				connex = new Connexion().setConnect();
				float reste = user.getSolde_compte()-sur.getMontant();
				if(reste<0)
				{
					throw new Exception("Votre solde est trop basse");
				}
				else
				{
					if(sur.getMontant()<enchere.get(0).getPrixminimum())
					{
						throw new Exception("le montant ajoute est trop bas");
					}
					connex.setAutoCommit(false);
					String requete2= "update utilisateur set solde_compte='"+reste+"' where idutilisateur='"+sur.getIdutilisateur()+"'";
					String requete3= "update enchere set prixdevente = '"+sur.getMontant()+"' where idenchere='"+sur.getIdenchere()+"'";
					PreparedStatement ps1 = connex.prepareStatement(requete);
					PreparedStatement ps2 = connex.prepareStatement(requete2);
					PreparedStatement ps3 = connex.prepareStatement(requete3);
					ps1.execute();
					ps2.executeUpdate();
					ps3.executeUpdate();
					retour = true;
					System.out.println(requete);
					System.out.println(requete2);
					System.out.println(requete3);
					connex.commit();
					ps1.close();
					ps2.close();
					ps3.close();
				}
			}
		}
		catch(Exception e)
		{
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
