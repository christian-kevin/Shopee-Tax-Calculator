package com.kevin.app.service_impl.product;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import com.kevin.app.entity.product_tax.ProductTaxEntity;
import com.kevin.app.entity.tax.FoodAndBeverageTaxEntity;
import com.kevin.app.service_impl.tax.TaxFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductManagerImplTest {

    @InjectMocks
    ProductManagerImpl underTest;

    @Mock
    TaxFactoryImpl taxFactory;

    @Mock
    ProductsDaoService productsDaoService;

    @Test
    public void testPersistProductEntitySettingTaxCode() {
       ProductTaxEntity productTaxEntity = ProductTaxEntity.builder()
               .name("testName")
               .price(1000)
               .tax(new FoodAndBeverageTaxEntity())
               .build();

       when(taxFactory.getTaxEntity(1)).thenCallRealMethod();

       underTest.persistProductTaxEntity(productTaxEntity);

       ArgumentCaptor<Products> captor = ArgumentCaptor.forClass(Products.class);
       verify(productsDaoService, times(1)).persistProducts(captor.capture());
       assertEquals(captor.getValue().getName(), productTaxEntity.getName());
       assertTrue(captor.getValue().getProductTaxes().size() > 0);
       assertEquals(captor.getValue().getProductTaxes().get(0).getProduct(), captor.getValue());
       assertEquals(captor.getValue().getProductTaxes().get(0).getTaxCode(), productTaxEntity.getTax().getTaxCode());
    }
}
