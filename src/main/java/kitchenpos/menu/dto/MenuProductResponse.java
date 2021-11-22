package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuProduct;

import java.math.BigDecimal;

public class MenuProductResponse {
    private Long seq;
    private String productName;
    private BigDecimal price;
    private Long quantity;



    protected MenuProductResponse() {
    }

    private MenuProductResponse(Long seq, String productName, BigDecimal price, Long quantity) {
        this.seq = seq;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public static MenuProductResponse of (MenuProduct menuProduct) {
       return new MenuProductResponse(menuProduct.getSeq(), menuProduct.getProduct().getName(),
               menuProduct.getProduct().getPrice(), menuProduct.getQuantity());
    }
}
