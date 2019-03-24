package com.kevin.app.service.tax;

import com.kevin.app.entity.tax.ITaxEntity;

public interface ITaxFactory {
    ITaxEntity getTaxEntity(int taxCode);
}
