package kitchenpos.order.dto.request;

public class OrderLineItemRequest {
    private Long orderId;
    private Long menuId;
    private Long quantity;

    public OrderLineItemRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public long getQuantity() {
        return quantity;
    }
}
