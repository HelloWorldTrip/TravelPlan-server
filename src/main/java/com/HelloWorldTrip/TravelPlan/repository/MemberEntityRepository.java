package com.HelloWorldTrip.TravelPlan.repository;

import com.HelloWorldTrip.TravelPlan.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
}
