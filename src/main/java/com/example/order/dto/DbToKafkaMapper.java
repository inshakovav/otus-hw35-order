package com.example.order.dto;

import com.example.order.entity.OrderEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DbToKafkaMapper {
    @Mapping(target="orderId", source = "entity.id")
    OrderCreatedMessage dbToKafka(OrderEntity entity);
}
