package com.mock.docker.app.repositories;

import com.mock.docker.app.model.entities.PermissionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionTable, Long> {
    public List<PermissionTable> findAllByPermissionName(final String permissionName);
}
