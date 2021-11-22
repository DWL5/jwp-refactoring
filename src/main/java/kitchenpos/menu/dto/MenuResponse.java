package kitchenpos.menu.dto;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<MenuProductResponse> menuProductResponses;


    private MenuResponse(Long id, String name, BigDecimal price, List<MenuProductResponse> menuProductResponses) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuProductResponses = menuProductResponses;
    }

    public static MenuResponse of(Menu menu) {
        List<MenuProductResponse> menuProductResponses = new ArrayList<>();

        for (MenuProduct menuProduct : menu.getMenuProducts()) {
            menuProductResponses.add(MenuProductResponse.of(menuProduct));
        }
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(), menuProductResponses);
    }

    public static List<MenuResponse> toList(List<Menu> menus) {
        List<MenuResponse> menuResponses = new ArrayList<>();
        for (Menu menu : menus) {
            menuResponses.add(MenuResponse.of(menu));
        }
        return menuResponses;
    }
}
