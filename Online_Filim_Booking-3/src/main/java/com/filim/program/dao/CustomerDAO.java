package com.filim.program.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.filim.program.dto.Customers;
import com.filim.program.dto.DateOperation;
import com.filim.program.dto.MovieDetails;
import com.filim.program.dto.OrderHistory;
import com.filim.program.dto.Seats;

@Component
public class CustomerDAO 
{
	  @Autowired
      private CustomerRepository cr;
	  @Autowired
	  private SeatRepository sr;
	  @Autowired
	  private MovieRepository mr;
	  @Autowired
	  private OrderHistoryRepository ohr;
	  
	  
	  
	  public int customerSave(Customers customer)
	   {
		 cr.save(customer); 
		 return 1;
	   }
	  
	  public Customers loginProsesing(String email,String password)
	  {
		  Customers customers=cr.findByEmailAndPassword(email, password);
		return customers;
		  
	  }
	  
	  public int seatSave(Seats seat,Customers customer,Date date,String time)
	  {
		  List<Seats> l1=new ArrayList<Seats>();
		  l1.add(seat);
		  customer.setSeat(l1);
		  DateOperation d1=new DateOperation();
		  
		  d1.setShowDate(date);
		  d1.setShowTime(time);
		  d1.setSeat(l1);
		  
		  seat.setOperation(d1);
		  seat.setCustomer(customer);
		  sr.save(seat);
		  return 1;
		  
	  }
	  
	    public List<Seats> getSeats(long id)
	    {
	    	List<Seats> lis=sr.getAllSeat(id);
			return lis;
	    	
	    }
	    
	    public List<Customers> getAll()
	    {
	    	List<Customers> customall=cr.findAll();
			return customall;
	    	
	    }
	    
	    public OrderHistory historySave(OrderHistory OrderHistory,Customers customer)
	    {
	    	customer.setOrderhistory(OrderHistory);
	    	OrderHistory ohsave=ohr.save(OrderHistory);
			return ohsave;
	    	
	    }
	    public List<OrderHistory> getAllHistory(long id){
			List<OrderHistory> list = ohr.getAllOrderHistory(id);		
			return list;
		}
	    public List<Seats> getAllSeat(LocalDate date, String time){
			List<Seats> list = sr.getAllByDate(date, time);
			return list;
		}
		
		public void delete(long id) {
			cr.deleteById(id);
		}
		
		public int updateDetail(Customers customer) {
			cr.save(customer);
			return 1;
		}
		
		public List<MovieDetails> getAllMovie(){
			List<MovieDetails> list = this.mr.findAll();
			return list;
		}

		
}
		
