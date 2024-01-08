package com.zb.reservation.reservation.mapper;

import com.zb.reservation.reservation.dto.ReservationDto;
import com.zb.reservation.reservation.entity.Reservation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation toEntity(ReservationDto reservationDto);

    @Named("toDto")
    @Mapping(source = "id", target = "reservationId")
    ReservationDto toDto(Reservation reservation);

    @IterableMapping(qualifiedByName = "toDto")
    List<ReservationDto> toDtoList(List<Reservation> reservationList);

}
