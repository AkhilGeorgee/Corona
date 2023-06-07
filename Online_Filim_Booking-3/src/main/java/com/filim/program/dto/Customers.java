package com.filim.program.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_Table")
public class Customers 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
    private long cId;
	
	@Column(name = "Customer_Name",nullable = false)
    private String cName;
	
	@Column(name = "Customer_Email",nullable = false,unique = true)
    private String email;
	
	@Column(name = "Customer_Password",nullable = false)
    private String password;
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
	private List<Seats> seat;
	
	@OneToOne(mappedBy = "customers",fetch = FetchType.EAGER)
	private OrderHistory orderhistory;

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Seats> getSeat() {
		return seat;
	}

	public void setSeat(List<Seats> seat) {
		this.seat = seat;
	}

	public OrderHistory getOrderhistory() {
		return orderhistory;
	}

	public void setOrderhistory(OrderHistory orderhistory) {
		this.orderhistory = orderhistory;
	}

	public Customers(long cId, String cName, String email, String password, List<Seats> seat,
			OrderHistory orderhistory) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.email = email;
		this.password = password;
		this.seat = seat;
		this.orderhistory = orderhistory;
	}

	public Customers(String cName, String email, String password, List<Seats> seat, OrderHistory orderhistory) {
		super();
		this.cName = cName;
		this.email = email;
		this.password = password;
		this.seat = seat;
		this.orderhistory = orderhistory;
	}

	public Customers() {
		super();
		System.out.println(this.getClass().getSimpleName()+"Object Created");
	}

	@Override
	public String toString() {
		return "Customers [cId=" + cId + ", cName=" + cName + ", email=" + email + ", password=" + password + ", seat="
				+ seat + ", orderhistory=" + orderhistory + "]";
	}
	
	
}
