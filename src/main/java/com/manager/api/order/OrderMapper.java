package com.manager.api.order;

import com.manager.api.item.Item;
import com.manager.api.item.ItemMapper;

import java.util.Collection;
import java.util.stream.Collectors;

public class OrderMapper {
    private OrderMapper() {
    }

    public static OrderDto mapToDto(Order order) {
        if (order == null) {
            return null;
        }

        return createNewOrderDto(order);
    }

    public static Collection<OrderDto> mapCollectionToDto(Collection<Order> orders) {
        if (orders == null) {
            return null;
        }

        return orders.stream().map(OrderMapper::mapToDto).collect(Collectors.toList());
    }

    public static Order mapDtoToEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        return new Order(orderDto.getTitle());
    }

    public static Order mapCreateDtoToEntity(OrderCreateDto createDto) {
        if (createDto == null) {
            return null;
        }

        Collection<Item> items = ItemMapper.mapDtoCollectionToEntity(createDto.getItems());

        return new Order(
                createDto.getTitle(),
                items
        );
    }

    private static OrderDto createNewOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getTitle(),
                order.getState(),
                order.getCreatedAt(),
                ItemMapper.mapCollectionToDto(order.getItems())
        );
    }


}
