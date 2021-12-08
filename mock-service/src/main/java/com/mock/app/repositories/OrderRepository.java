package com.mock.app.repositories;

import com.mock.app.model.entities.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderTable, Long> {
    public List<OrderTable> findOrderTableByUserId(final Long userId);
}
