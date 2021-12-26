package com.mock.docker.app.repositories;

import com.mock.docker.app.model.entities.RoleTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleTable, Long> {
    public List<RoleTable> findAllByRoleName(final String roleName);
}
