package com.mock.app.repositories;

import com.mock.app.model.entities.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    public UserTable findUserTableByUserName(final String userName);
}
