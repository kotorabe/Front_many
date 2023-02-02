package com.example.enchere.ModelAdmin;

import java.sql.Connection;
import java.sql.Statement;

import com.example.enchere.Base.Connexion;

public class Commission {
	private int idenchere;
	private float commission;
	public int getIdenchere() {
		return idenchere;
	}
	public void setIdenchere(int idenchere) {
		this.idenchere = idenchere;
	}
	public float getCommission() {
		return commission;
	}
	public void setCommission(float commission) {
		this.commission = commission;
	}
	
	public boolean create(Commission commission) throws Exception
	{
		String requete = "insert into commission values('"+commission.getIdenchere()+"','"+commission.getCommission()+"')";
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
	
}
