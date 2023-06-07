package com.filim.program.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filim.program.dto.OrderHistory;
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long>
{
	@Query(value = "select * from order_history_table where customers_c_id=? ORDER BY id DESC",nativeQuery = true)
    public List<OrderHistory> getAllOrderHistory(long id);
}
