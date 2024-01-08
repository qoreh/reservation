package com.zb.reservation.review.mapper;

import com.zb.reservation.review.dto.ReviewInputDto;
import com.zb.reservation.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);
    Review toEntity(ReviewInputDto.Add reviewInputDto);

    void updateEntity(ReviewInputDto.Edit reviewInputDto, @MappingTarget Review review);
}
