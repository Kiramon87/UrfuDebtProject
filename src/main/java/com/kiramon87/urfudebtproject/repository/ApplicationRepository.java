package com.kiramon87.urfudebtproject.repository;


import com.kiramon87.urfudebtproject.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
