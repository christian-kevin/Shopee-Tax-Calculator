package com.kevin.app.service_impl.tax;

import static org.junit.Assert.assertEquals;

import com.kevin.app.service.tax.ITaxFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaxFactoryImplTest {
    ITaxFactory underTest;

    @Before
    public void init() {
        underTest = new TaxFactoryImpl();
    }

    @Test
    public void testEntityGeneratedHasSameTaxCode() {
        assertEntityTaxCode(1);
        assertEntityTaxCode(2);
        assertEntityTaxCode(3);
    }

    private void assertEntityTaxCode(int taxCode) {
        assertEquals(underTest.getTaxEntity(taxCode).getTaxCode(), taxCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTaxCodeClassNotDefined() {
        assertEntityTaxCode(4);
    }
}
