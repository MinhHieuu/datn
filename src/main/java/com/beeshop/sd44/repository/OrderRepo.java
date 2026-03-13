package com.beeshop.sd44.repository;

import com.beeshop.sd44.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order, UUID> {
	List<Order> findByUserId(UUID userId);
	List<Order> findAllByOrderByCreatedAtDesc();

	@Query("SELECT o FROM Order o WHERE " +
	       "(:status IS NULL OR o.status = :status) AND " +
	       "(:paymentStatus IS NULL OR o.paymentStatus = :paymentStatus) AND " +
	       "(:type IS NULL OR o.type = :type) AND " +
	       "(:paymentMethod IS NULL OR o.paymentMethod = :paymentMethod) AND " +
	       "(:fromDate IS NULL OR o.createdAt >= :fromDate) AND " +
	       "(:toDate IS NULL OR o.createdAt <= :toDate) " +
	       "ORDER BY o.createdAt DESC")
	List<Order> findOrdersByFilter(
		@Param("status") Integer status,
		@Param("paymentStatus") Integer paymentStatus,
		@Param("type") Integer type,
		@Param("paymentMethod") String paymentMethod,
		@Param("fromDate") Date fromDate,
		@Param("toDate") Date toDate
	);
}
