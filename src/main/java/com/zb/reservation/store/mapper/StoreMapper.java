package com.zb.reservation.store.mapper;

import com.zb.reservation.store.dto.StoreDetailDto;
import com.zb.reservation.store.dto.StoreManagement;
import com.zb.reservation.store.entity.Store;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StoreMapper {

    StoreMapper INSTANCE = Mappers.getMapper(StoreMapper.class);

    @Mapping(target = "location", expression = "java(toPoint(addDto.getLnt(), addDto.getLat()))")
    @Mapping(target = "score", constant = "0")
    @Mapping(target = "verificationCode", source = "verificationCode")
    Store toEntity(StoreManagement.addDto addDto, String verificationCode);

    @Mapping(target = "location", expression = "java(toPoint(editDto.getLnt(), editDto.getLat()))")
    void updateFromDto(StoreManagement.editDto editDto, @MappingTarget Store store);

    default Point toPoint(double lnt, double lat) {
        return new GeometryFactory().createPoint(new Coordinate(lnt, lat));
    }

    StoreDetailDto toDto(Store store);

}
