package com.project3.revtech.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project3.revtech.dao.BundleRepository;
import com.project3.revtech.dao.ProductRepository;
import com.project3.revtech.entity.BundleEntity;
import com.project3.revtech.entity.ProductEntity;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.BundlePojo;
import com.project3.revtech.pojo.ProductPojo;

import org.springframework.boot.test.mock.mockito.MockBean;


@ContextConfiguration(classes= {BundleServiceImpl.class})
@ExtendWith(SpringExtension.class)

public class BundleServiceImplTest {
	
	@MockBean
	BundleRepository bundleRepository;
	
	@MockBean
	ProductRepository productRepository;
	
	@Autowired
	BundleServiceImpl bundleServiceImpl;

	@Test
	public void testGetAllBundles() throws ApplicationException {
		List<BundleEntity> bundleEntity= new ArrayList<>();
		
		ProductEntity productTest1 = new ProductEntity();
		productTest1.setProductId(1);
		ProductEntity productTest2 = new ProductEntity();
		productTest1.setProductId(2);
		
		ProductEntity productTest3 = new ProductEntity();
		productTest3.setProductId(3);
		ProductEntity productTest4 = new ProductEntity();
		productTest4.setProductId(4);
		
		BundleEntity bundleTest1 = new BundleEntity();
		bundleTest1.setBundleId(1);
		bundleTest1.setBundleName("Dev1");
		bundleTest1.setBundlePercentage(BigDecimal.valueOf(15L));
		bundleTest1.setProductOneEntity(productTest1);
		bundleTest1.setProductTwoEntity(productTest2);
	
		BundleEntity bundleTest2 = new BundleEntity();
		bundleTest2.setBundleId(2);
		bundleTest2.setBundleName("Dev2");
		bundleTest2.setBundlePercentage(BigDecimal.valueOf(30L));
		bundleTest2.setProductOneEntity(productTest3);
		bundleTest2.setProductTwoEntity(productTest4);
	
		
		List<BundleEntity> bundleList= new ArrayList<>();
		bundleList.add(bundleTest2);
		bundleList.add(bundleTest1);
		when(this.bundleRepository.findAll()).thenReturn(bundleList);
		List<BundlePojo> actualResult = this.bundleServiceImpl.getAllBundles();
		assertNotNull(actualResult);
		assertEquals(2,actualResult.size());
		
	}

    @Test
    public void getAllBundles()throws ApplicationException {
        when(this.bundleRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.bundleServiceImpl.getAllBundles().isEmpty());
        verify(this.bundleRepository).findAll();
    }
    
    @Test
    public void createBundle() throws ApplicationException{
    	BundleEntity bundleEntity = new BundleEntity();
    	bundleEntity.setBundleId(1);
    	bundleEntity.setBundleName("first");
    	bundleEntity.setBundlePercentage(null);
    	bundleEntity.setProductOneEntity(new ProductEntity());
    	bundleEntity.setProductTwoEntity(new ProductEntity());
        
        ProductEntity productTest1 = new ProductEntity();
        productTest1.setImageUrl("sdfa");
        productTest1.setProductCategory("these");
        productTest1.setProductCost(BigDecimal.valueOf(30L));
        productTest1.setProductDescription("not");
        productTest1.setProductName("done");
        productTest1.setProductQty(1);
        productTest1.setProductRemoved(false);
        productTest1.setProductSku("134784");
        productTest1.setProductId(2);
        
        ProductEntity productTest2 = new ProductEntity();
        productTest2.setImageUrl("sdfa");
        productTest2.setProductCategory("these");
        productTest2.setProductCost(BigDecimal.valueOf(30L));
        productTest2.setProductDescription("not");
        productTest2.setProductName("done");
        productTest2.setProductQty(1);
        productTest2.setProductRemoved(false);
        productTest2.setProductSku("134784");
        productTest2.setProductId(2);
        
     	BundleEntity bundleEntity1 = new BundleEntity();
     	bundleEntity1.setBundleId(1);
     	bundleEntity1.setBundleName("first");
     	bundleEntity1.setBundlePercentage(BigDecimal.valueOf(30L));
     	bundleEntity1.setProductOneEntity(productTest1);
     	bundleEntity1.setProductTwoEntity(productTest2);
 

        when(this.productRepository.findByProductId(1)).thenReturn(productTest1);
        when(this.productRepository.findByProductId(2)).thenReturn(productTest2);
        when(this.bundleRepository.saveAndFlush((BundleEntity) any())).thenReturn(bundleEntity1);
        BundlePojo bundlePojo = new BundlePojo(3, "DEV3", BigDecimal.valueOf(42L), null, null);
   
        
        BundlePojo actualResult = this.bundleServiceImpl.createBundle(bundlePojo);
        assertNotNull(actualResult);
        assertSame(bundlePojo, actualResult);
        assertEquals("first", actualResult.getBundleName());
       
    }



}
