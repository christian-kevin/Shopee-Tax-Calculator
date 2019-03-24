package com.kevin.app.entity.tax;

import java.math.BigDecimal;

public interface ITax {
    int getTaxCode();
    String getType();
    boolean isRefundable();
    BigDecimal calculateTax(int price);
}
