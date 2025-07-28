package top.academy.springmvc;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Order {
    private Long id;
    private String customerName;
    private String customerEmail;
    private String itemName;
    private int quantity;
    private BigDecimal price;
    private String status;
    private LocalDateTime orderDate;
    
    public BigDecimal getTotalAmount() {
        if (price != null && quantity > 0) {
            return price.multiply(BigDecimal.valueOf(quantity));
        }
        return BigDecimal.ZERO;
    }
}