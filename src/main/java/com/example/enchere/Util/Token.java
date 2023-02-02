package com.example.enchere.Util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class Token {
	private String token;
    private Date expire;
    private int idutilisateur;
	public int getIdutilisateur() {
		return idutilisateur;
	}
	public void setIdutilisateur(int idutilisateur) {
		this.idutilisateur = idutilisateur;
	}
	public String tokengenerator(String token,String requete) throws NoSuchAlgorithmException
    {
        String toash = token + requete;
        try {
            
            this.setExpire();
            return Token.toAsh(toash);


        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            throw e;
        }


    }
	
	public static String toAsh(String requete) throws NoSuchAlgorithmException
	{
		
		MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(requete.getBytes());
        BigInteger no = new BigInteger(1,digest);
        String hashtext = no.toString(16);
        while(hashtext.length()<32)
        {
            hashtext = "0"+hashtext;
        }
        return hashtext;
        
	}
	private void setExpire() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);

        this.expire = cal.getTime();
    }
	public void setExpire(Date date)
	{
		this.expire = date;
	}
	public String getToken() {
		return token;
	}
	public Date getExpire() {
		return expire;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}