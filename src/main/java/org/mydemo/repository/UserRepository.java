package org.mydemo.repository;

import org.mydemo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByAge(int age);
    boolean existsByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
    UserEntity findByEmailAndPassword(String email, String password);
}
