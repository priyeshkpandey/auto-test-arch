package com.mock.docker.app.repositories;

import com.mock.docker.app.model.entities.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductTable, Long> {
    public List<ProductTable> findAllByProductDescriptionContaining(final String query);
    public List<ProductTable> findAllByProductNameContaining(final String query);
}
