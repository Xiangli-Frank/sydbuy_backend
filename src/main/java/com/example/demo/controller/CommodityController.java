package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author haocun li
 * @date created in 2018/10/23 22:05
 * @since 1.0.0
 */




//@Controller表明该类所有方法默认返回页面路径，加了@ResponseBody的方法返回数据。@RestController返回的都是数据，不返回页面
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    //@Autowired 注入类对类成员变量，方法和构造函数标注，自动装配，消除set,get
    @Autowired
    FileUploadService fileUploadService;

    //@Autowired 注入类对类成员变量，方法和构造函数标注，自动装配，消除set,get
    @Autowired
    CommodityRepository commodityRepository;

    //@Autowired 注入类对类成员变量，方法和构造函数标注，自动装配，消除set,get
    @Autowired
    CommentRepository commentRepository;

    //@Autowired 注入类对类成员变量，方法和构造函数标注，自动装配，消除set,get
    @Autowired
    BuyRepository buyRepository;

    //向指定的资源提交要被处理的数据
    //upload the commodity picture
	@PostMapping(value="/upload")
	public RestEntity release(MultipartFile file) {
	        try {
            String  fileName = this.fileUploadService.photoUpload(file);
            return RestEntity.ok(fileName);
        } catch (IOException e) {
            return RestEntity.error(RestStatusEnum.UPLOAD_IMAGE__ERROR,e);
        }
	}

    //从指定的资源请求数据
    /*
    value的uri值为以下三类：
    A）可以指定为普通的具体值；
    B) 可以指定为含有某变量的一类值(URI Template Patterns with Path Variables)；
    C) 可以指定为含正则表达式的一类值( URI Template Patterns with Regular Expressions);
     */
    @GetMapping(value="/obtain/{username}/{pageSize}/{pageIndex}")
    //paramId可通过 @Pathvariable注解绑定它传过来的值到方法的参数上
    public RestEntity obtain(@PathVariable String username, @PathVariable Integer pageSize, @PathVariable Integer pageIndex) {
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<CommodityDTO> commodityDTOS = this.commodityRepository.findAllByUsername(username,pageRequest);
        return RestEntity.ok(commodityDTOS);
	}

    //添加商品
    @PostMapping(value="/add")
    public RestEntity add(@RequestBody CommodityDTO commodityDTO) {
        String id = commodityDTO.getId();//id of commodity
        CommodityDTO oldCommodityDTO = this.commodityRepository.findOne(id);//check whether the commodity exists or not
        if (oldCommodityDTO != null){
            return RestEntity.ok(RestStatusEnum.RELEASE_ERROR);
        }else {
            //The commodity is not existing, add it
            this.commodityRepository.save(commodityDTO);
        }
        return RestEntity.ok(RestStatusEnum.RELEASE_SUCCESS,null,id);
	}

    //查询商品
    @GetMapping("/getDetail/{id}")
    public RestEntity getDetail(@PathVariable String id){
        CommodityDTO commodityDTOS = this.commodityRepository.findOne(id);
        List<CommentDTO> commentDTOS = this.commentRepository.findAllByCommodity(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commodity",commodityDTOS);
        jsonObject.put("comment",commentDTOS);
        return RestEntity.ok(jsonObject);
    }

    /**
     * @author Xiang Li
     * @date created in 2018/10/20 23:49
     * @since 1.0.0
     */

//卖家获取订单信息,针对于特别买家的信息抓取
    @GetMapping("/listOrder/{username}/{pageSize}/{pageIndex}")
    public RestEntity listBuyDTO(@PathVariable String username,@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        //静态实现快速构造Pageable
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        List<CommodityDTO> commodityDTOS = this.commodityRepository.findAllByUsername(username);
        //将对象转换为其他对象
        List<String> ids = commodityDTOS.stream().map(commodityDTO -> commodityDTO.getId()).collect(Collectors.toList());
        //"order",相同username,商品id的List，page index&page size
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAllByStateAndCommodityIn("order",ids,pageRequest);
        return RestEntity.ok(buyDTOS);
    }
//针对于所有买家的信息抓取
	@GetMapping("/listOrder/{pageSize}/{pageIndex}")
    public RestEntity listAllBuyDTO(@PathVariable Integer pageSize,@PathVariable Integer pageIndex){
        PageRequest pageRequest = new PageRequest(pageIndex-1,pageSize);
        Page<BuyDTO> buyDTOS =  this.buyRepository.findAll(pageRequest);
        return RestEntity.ok(buyDTOS);
	}

	//订单操作核心方法 id为订单ID
	@PostMapping("/okOrder/{id}/{commodity}/{count}")
    @Transactional
    public RestEntity okOrder(@PathVariable Integer id,@PathVariable String commodity,@PathVariable Integer count){
        //check stock
        CommodityDTO commodityDTO = this.commodityRepository.findOne(commodity);
        Integer lave = commodityDTO.getLave();
        //如果库存小于需求，则报错
        if (lave < count){
            return RestEntity.error(RestStatusEnum.INVENTORY_SHORTAGE,null);
        }
        //stock - 1, add to database
        //得到count：卖出个数，传入进数据库sell
        commodityDTO.setSell(count);
        lave = lave-count;
        //更改剩余个数
        commodityDTO.setLave(lave);
        this.commodityRepository.save(commodityDTO);
        //change buy state
        BuyDTO buyDTO = this.buyRepository.findOne(id);
        buyDTO.setState("success");
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(RestStatusEnum.OK);
	}
	//取消订单，商品ID
	@PostMapping("/cancleOrder/{id}")
    public RestEntity cancleOrder(@PathVariable Integer id){
        //BuyDTO buyDTO = this.buyRepository.findOne(id);
        BuyDTO buyDTO = this.buyRepository.findOne(id);
        buyDTO.setState("fail");
        this.buyRepository.save(buyDTO);
        return RestEntity.ok(RestStatusEnum.OK);
	}



	 
}