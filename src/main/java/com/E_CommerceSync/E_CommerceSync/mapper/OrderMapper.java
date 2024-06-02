package com.E_CommerceSync.E_CommerceSync.mapper;

import com.E_CommerceSync.E_CommerceSync.dto.request.OrderRequest;
import com.E_CommerceSync.E_CommerceSync.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "status", constant = "COMPLETED")
    Order orderRequestToOrder(OrderRequest request);
}
