package com.filim.program.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Seat_Table")
public class Seats 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_id")
     private long sId;
     @Column(name = "Seat_No")
     @ElementCollection
     private List<String> sNo;
     
     @Column(name = "Seat_Price")
     @ElementCollection
     private List<Double> sprice;
     
     @Column(name = "Seat_Total")
     private double total;
     @ManyToOne
     private Customers customer;
     
     @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     private DateOperation operation;

	public long getsId() {
		return sId;
	}

	public void setsId(long sId) {
		this.sId = sId;
	}

	public List<String> getsNo() {
		return sNo;
	}

	public void setsNo(List<String> sNo) {
		this.sNo = sNo;
	}

	public List<Double> getSprice() {
		return sprice;
	}

	public void setSprice(List<Double> sprice) {
		this.sprice = sprice;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public DateOperation getOperation() {
		return operation;
	}

	public void setOperation(DateOperation operation) {
		this.operation = operation;
	}

	public Seats(long sId, List<String> sNo, List<Double> sprice, double total, Customers customer,
			DateOperation operation) {
		super();
		this.sId = sId;
		this.sNo = sNo;
		this.sprice = sprice;
		this.total = total;
		this.customer = customer;
		this.operation = operation;
	}

	public Seats(List<String> sNo, List<Double> sprice, double total, Customers customer, DateOperation operation) {
		super();
		this.sNo = sNo;
		this.sprice = sprice;
		this.total = total;
		this.customer = customer;
		this.operation = operation;
	}

	public Seats()
	{
		super();
		System.out.println(this.getClass().getSimpleName()+"Object Created");
	}

	@Override
	public String toString() {
		return "Seats [sId=" + sId + ", sNo=" + sNo + ", sprice=" + sprice + ", total=" + total + ", customer="
				+ customer + ", operation=" + operation + "]";
	}
}
