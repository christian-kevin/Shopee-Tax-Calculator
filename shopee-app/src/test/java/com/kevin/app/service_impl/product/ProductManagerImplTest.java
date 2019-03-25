package com.kevin.app.service_impl.product;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.kevin.app.dao.product_taxes.ProductTaxes;
import com.kevin.app.dao.products.Products;
import com.kevin.app.dao.products.ProductsDaoService;
import com.kevin.app.entity.product_tax.BillEntity;
import com.kevin.app.entity.product_tax.ProductTaxEntity;
import com.kevin.app.entity.tax.FoodAndBeverageTaxEntity;
import com.kevin.app.service_impl.tax.TaxFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductManagerImplTest {

    @InjectMocks
    ProductManagerImpl underTest;

    @Mock
    TaxFactoryImpl taxFactory;

    @Mock
    ProductsDaoService productsDaoService;

    @Mock
    Products productsMock;

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

    @Test(expected = IllegalArgumentException.class)
    public void testPersistProductEntityAttributeNotComplete() {
        ProductTaxEntity productTaxEntity = ProductTaxEntity.builder()
                .name("testName")
                .price(1000)
                .build();

        underTest.persistProductTaxEntity(productTaxEntity);
    }

    private void setupGetAllProducts(String productName, int price, int taxCode) {
        when(productsDaoService.getAllProducts()).thenReturn(Collections.singletonList(productsMock));
        when(productsMock.getName()).thenReturn(productName);
        when(productsMock.getPrice()).thenReturn(price);
        when(productsMock.getProductTaxes()).thenReturn(Collections.singletonList(new ProductTaxes(productsMock, taxCode)));
        when(taxFactory.getTaxEntity(taxCode)).thenCallRealMethod();
    }

    @Test
    public void testGetAllProducts() {
        String productName = "testProduct";
        int price = 1000;
        int taxCode = 1;

        setupGetAllProducts(productName, price, taxCode);

        List<ProductTaxEntity> result = underTest.getAllProducts();

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), productName);
        assertEquals(result.get(0).getPrice(), price);
        assertEquals(result.get(0).getTax().getTaxCode(), taxCode);
        assertEquals(result.get(0).getTaxAmount(), taxFactory.getTaxEntity(taxCode).calculateTax(price));
        assertEquals(result.get(0).getAfterTaxAmount(), new BigDecimal(price).add(result.get(0).getTaxAmount()));
    }

    @Test
    public void testCreateBill() {
        String productName = "testProduct";
        int price = 1000;
        int taxCode = 1;

        setupGetAllProducts(productName, price, taxCode);

        Optional<BillEntity> billEntityOpt = underTest.createBill();

        assertTrue(billEntityOpt.isPresent());
        BillEntity billEntity = billEntityOpt.get();

        assertEquals(billEntity.getPriceSubtotal(), new BigDecimal(price));
        assertEquals(billEntity.getTaxSubtotal(), taxFactory.getTaxEntity(taxCode).calculateTax(price));
        assertEquals(billEntity.getGrandTotal(), billEntity.getPriceSubtotal().add(billEntity.getTaxSubtotal()));
    }
}
