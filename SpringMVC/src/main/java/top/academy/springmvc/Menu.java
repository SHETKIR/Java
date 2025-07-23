package top.academy.springmvc;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Menu {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
}