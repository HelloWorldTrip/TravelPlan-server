package com.HelloWorldTrip.TravelPlan.repository;


import com.HelloWorldTrip.TravelPlan.domain.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginEntityRepository extends JpaRepository<LoginEntity, Long> {

}
