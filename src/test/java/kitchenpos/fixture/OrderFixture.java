package kitchenpos.fixture;

import java.util.Arrays;

import kitchenpos.domain.Order;
import kitchenpos.domain.OrderStatus;
import kitchenpos.dto.request.OrderChangeStatusRequest;
import kitchenpos.dto.request.OrderCreateRequest;
import kitchenpos.dto.request.OrderLineItemCreateRequest;

public class OrderFixture {
    public static final Long ID1 = 1L;
    public static final String MEAL_STATUS = OrderStatus.MEAL.name();
    public static final String COOKING_STATUS = OrderStatus.COOKING.name();
    public static final String COMPLETION = OrderStatus.COMPLETION.name();

    public static OrderCreateRequest createRequest(Long orderTableId,
        OrderLineItemCreateRequest... requests) {
        return new OrderCreateRequest(orderTableId, Arrays.asList(requests));
    }

    public static Order createWithoutId(String status, Long tableId) {
        return Order.of(null, tableId, status);
    }

    public static Order createWithId(Long id, String status, Long tableId) {
        return Order.of(id, tableId, status);
    }

    public static OrderChangeStatusRequest changeStatusRequest(OrderStatus status) {
        return new OrderChangeStatusRequest(status.name());
    }
}