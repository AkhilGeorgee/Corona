package com.filim.program.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filim.program.dto.Seats;
@Repository
public interface SeatRepository extends JpaRepository<Seats, Long>
{
	 @Query(value = "select * from Seat_Table where customers_c_id=?",nativeQuery = true)
     public List<Seats> getAllSeat(long id);
	 
	 @Query(value = "select * from seat_table inner join seats_s_no"
				+ " on seat_table.s_id = seats_s_no.seats_s_id"
				+ " inner join date_operation"
				+ " on seat_table.operation_date_id = date_operation.date_id"
				+ " where show_date = ? and show_time = ?", nativeQuery = true)
	 public List<Seats> getAllByDate(LocalDate date,String time);
}
