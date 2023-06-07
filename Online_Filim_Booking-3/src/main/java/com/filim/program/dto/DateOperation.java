package com.filim.program.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "date_operation")
public class DateOperation 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "date_id")
	private long DateId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "show_date")
	private Date showDate;
	
	@Column(name = "show_time")
	private String showTime;
	
	@OneToMany(mappedBy = "operation", fetch = FetchType.EAGER)
	private List<Seats> seat;

	public long getDateId() {
		return DateId;
	}

	public void setDateId(long dateId) {
		DateId = dateId;
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

	public List<Seats> getSeat() {
		return seat;
	}

	public void setSeat(List<Seats> seat) {
		this.seat = seat;
	}

	public DateOperation(long dateId, Date showDate, String showTime, List<Seats> seat) {
		super();
		DateId = dateId;
		this.showDate = showDate;
		this.showTime = showTime;
		this.seat = seat;
	}

	public DateOperation(Date showDate, String showTime, List<Seats> seat) {
		super();
		this.showDate = showDate;
		this.showTime = showTime;
		this.seat = seat;
	}

	public DateOperation()
	{
		super();
		System.out.println(this.getClass().getSimpleName()+"Object Created");
	}

	@Override
	public String toString() {
		return "DateOperation [DateId=" + DateId + ", showDate=" + showDate + ", showTime=" + showTime + ", seat="
				+ seat + "]";
	}
	
	

}
