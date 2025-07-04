package org.example.prj1.repository;


import org.example.prj1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface UserRepository extends JpaRepository<User, Integer> {
        boolean existsByUsername(String username);

        Optional<User> findByUsername(String username);
    }


