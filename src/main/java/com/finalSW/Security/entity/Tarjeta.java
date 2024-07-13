package com.finalSW.Security.entity;

public class Tarjeta {
	private String numeroTarjeta;
	private String titular;
	private String expmm;
	private String expyy;
	private String cvv;
	public Tarjeta(String numeroTarjeta, String titular, String expmm, String expyy, String cvv) {
		this.numeroTarjeta = numeroTarjeta;
		this.titular = titular;
		this.expmm = expmm;
		this.expyy = expyy;
		this.cvv = cvv;
	}
	public Tarjeta() {
		
	}
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
	}
	public String getExpmm() {
		return expmm;
	}
	public void setExpmm(String expmm) {
		this.expmm = expmm;
	}
	public String getExpyy() {
		return expyy;
	}
	public void setExpyy(String expyy) {
		this.expyy = expyy;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
}
