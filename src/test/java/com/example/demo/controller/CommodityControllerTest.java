package com.example.demo.controller;

import com.example.demo.entity.BuyDTO;
import com.example.demo.repository.BuyRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.ejb.EJB;

@RunWith(Arquillian.class)
public class CommodityControllerTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CommodityController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @Inject
    CommodityController commodityController;

    @EJB
    BuyRepository buyRepository;
    public void order_should_be_persistent() {
        commodityController.add(new CommodityDTO());
        Integer pageNoFromWeb = 1;
        //
        /**
         * 前台传来的每页几条数据（当前页面的记录数）
         */
        Integer pageSizeFromWeb = 5;
        Integer page = pageNoFromWeb - 1;
        Integer size = pageSizeFromWeb;
        PageRequest pageRequest = new PageRequest(page, size);
        Page<BuyDTO> buyDTOS = buyRepository.findAll(pageRequest);
        // 总记录数（总数据条数）
        int number = buyDTOS.getNumber();
        System.out.println(number);

    }


}
