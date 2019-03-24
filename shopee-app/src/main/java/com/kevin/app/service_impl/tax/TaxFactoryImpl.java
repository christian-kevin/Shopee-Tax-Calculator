package com.kevin.app.service_impl.tax;

import com.kevin.app.entity.tax.EntertainmentTaxEntity;
import com.kevin.app.entity.tax.FoodAndBeverageTaxEntity;
import com.kevin.app.entity.tax.ITaxEntity;
import com.kevin.app.entity.tax.TobaccoTaxEntity;
import com.kevin.app.service.tax.ITaxFactory;
import org.springframework.stereotype.Service;

@Service
public class TaxFactoryImpl implements ITaxFactory {

    public ITaxEntity getTaxEntity(int taxCode) {
        switch (taxCode) {
            case 1:
                return new FoodAndBeverageTaxEntity();
            case 2:
                return new TobaccoTaxEntity();
            case 3:
                return new EntertainmentTaxEntity();
        }
        throw new IllegalArgumentException("No tax entity exist");
    }
}
