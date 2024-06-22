package com.HelloWorldTrip.TravelPlan.mapper;

import com.HelloWorldTrip.TravelPlan.domain.MemberEntity;
import com.HelloWorldTrip.TravelPlan.dto.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO toDTO(MemberEntity memberEntity);

    MemberEntity toEntity(MemberDTO memberDTO);

}
