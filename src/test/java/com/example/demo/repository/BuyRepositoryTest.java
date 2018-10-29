package com.example.demo.repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;

import javax.ejb.EJB;


@RunWith(Arquillian.class)
public class BuyRepositoryTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BuyRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
   @EJB BuyRepository buyRepository;
           @Test
           public void order_should_be_persistent() {
               Assert.assertEquals(1,buyRepository.toString());
               System.out.println(buyRepository.getClass().getName());
           }
}
