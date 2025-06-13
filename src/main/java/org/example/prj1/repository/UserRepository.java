package org.example.prj1.repository;


import org.example.prj1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface UserRepository extends JpaRepository<User, Integer> {
        boolean existsByUsername(String username);

    }


