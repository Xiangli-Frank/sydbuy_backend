package com.example.demo.controller;

import com.example.demo.entity.BuyDTO;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haocunli
 * @date created in 2018/10/24 10:42
 * @since 1.0.0
 */

public class MallController {

    @Autowired
    CommodityRepository commodityRepository;

    @Autowired
    BuyRepository buyRepository;

    //首页分类显示商品
    @GetMapping("/listCommodityDTO/{category}")
    public RestEntity listCommodityDTO(@PathVariable String category){
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAll();
        //get commodity that its stock is not empty
        commodityDTOS = commodityDTOS.stream().filter(commodityDTO -> commodityDTO.getLave() != 0)
                .filter(commodityDTO -> commodityDTO.getCategory().equals(category)).collect(Collectors.toList());
        return RestEntity.ok(commodityDTOS);
    }

    //首页分类显示商品，搜索功能
    @GetMapping("/listCommodityDTO/{category}/{keyword}")
    public RestEntity listCommodityDTO(@PathVariable String category,@PathVariable String keyword){
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAll();
        commodityDTOS = commodityDTOS.stream().filter(commodityDTO -> commodityDTO.getLave() != 0)
                .filter(commodityDTO -> commodityDTO.getCategory().equals(category))
                .filter(commodityDTO -> commodityDTO.getName().indexOf(keyword) != -1).collect(Collectors.toList());
        return RestEntity.ok(commodityDTOS);
    }

    /**
     * @author Xiang Li
     * @date created in 2018/10/24 10:42
     * @since 1.0.0
     */
//@Responsebody 注解表示该方法的返回的结果直接写入 HTTP 响应正文
    @PostMapping("/buyCommodityDTO")
    public RestEntity buyCommodityDTO(@RequestBody BuyDTO buyDTO){
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(buyDTO);
    }

    //买家管理端 获取买家的订单信息
    @GetMapping("/listBuyDTO/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username, @PathVariable Integer pageSize, @PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAllByUsername(username,pageRequest);
        return RestEntity.ok(buyDTOS);
    }
}
