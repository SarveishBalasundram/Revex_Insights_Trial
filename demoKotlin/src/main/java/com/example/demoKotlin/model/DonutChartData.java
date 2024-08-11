package com.example.demoKotlin.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DonutChartData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private double  avg_positive_amount;
	private double avg_absolute_negative_amount;
	private int department_id;
	//private String dateTo;
	//private String dateFrom;
	private Date general_ledger_date;
	
	
	
//	public String getDateTo() {
//		return dateTo;
//	}
//	public void setDateTo(String dateTo) {
//		this.dateTo = dateTo;
//	}
//	public String getDateFrom() {
//		return dateFrom;
//	}
//	public void setDateFrom(String dateFrom) {
//		this.dateFrom = dateFrom;
//	}
	@JsonProperty("general_ledger_date")
	public Date getGeneral_ledger_date() {
		return general_ledger_date;
	}
	public void setGeneral_ledger_date(Date general_ledger_date) {
		this.general_ledger_date = general_ledger_date;
	}

	public DonutChartData(int deptId, double d, double e ) {
		this.setAvg_absolute_negative_amount(e);
		this.setAvg_positive_amount(d);
		this.setDepartment_id(deptId);
		//this.setGeneral_ledger_date(generalDate);// TODO Auto-generated constructor stub
	}
	@JsonProperty("avg_positive_amount")
	public double getAvg_positive_amount() {
		return avg_positive_amount;
	}
	public void setAvg_positive_amount(double avg_positive_amount) {
		this.avg_positive_amount = avg_positive_amount;
	}
	@JsonProperty("avg_absolute_negative_amount")
	public double getAvg_absolute_negative_amount() {
		return avg_absolute_negative_amount;
	}
	public void setAvg_absolute_negative_amount(double avg_absolute_negative_amount) {
		this.avg_absolute_negative_amount = avg_absolute_negative_amount;
	}
	
	
	@JsonProperty("department_id")
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	
	


}