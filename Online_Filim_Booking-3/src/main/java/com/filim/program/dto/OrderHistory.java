package com.filim.program.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "order_history_table")
public class OrderHistory
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long ohId;
	
    @ElementCollection
    private List<String> seat;
    
    @ElementCollection
    private List<Double> price;
    
    @Column(name = "Total")
    private double total;
    
    private String movieName;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "book_on_date")
    private Date bookingDate;
    
    @Temporal(value = TemporalType.DATE)
    @Column(name = "show_on_date")
    private Date showDate;
    
    @Column(name = "show_time")
    private String showTime;
    
    @OneToOne
    private Customers customers;

	public long getOhId() {
		return ohId;
	}

	public void setOhId(long ohId) {
		this.ohId = ohId;
	}

	public List<String> getSeat() {
		return seat;
	}

	public void setSeat(List<String> seat) {
		this.seat = seat;
	}

	public List<Double> getPrice() {
		return price;
	}

	public void setPrice(List<Double> price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	public OrderHistory(long ohId, List<String> seat, List<Double> price, double total, String movieName,
			Date bookingDate, Date showDate, String showTime, Customers customers) {
		super();
		this.ohId = ohId;
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.movieName = movieName;
		this.bookingDate = bookingDate;
		this.showDate = showDate;
		this.showTime = showTime;
		this.customers = customers;
	}

	public OrderHistory(List<String> seat, List<Double> price, double total, String movieName, Date bookingDate,
			Date showDate, String showTime, Customers customers) {
		super();
		this.seat = seat;
		this.price = price;
		this.total = total;
		this.movieName = movieName;
		this.bookingDate = bookingDate;
		this.showDate = showDate;
		this.showTime = showTime;
		this.customers = customers;
	}

	public OrderHistory()
	{
		super();
		
		System.out.println(this.getClass().getSimpleName()+"Object Created");
	}

	@Override
	public String toString() {
		return "OrderHistory [ohId=" + ohId + ", seat=" + seat + ", price=" + price + ", total=" + total
				+ ", movieName=" + movieName + ", bookingDate=" + bookingDate + ", showDate=" + showDate + ", showTime="
				+ showTime + ", customers=" + customers + "]";
	}
    
       
    
        
}
