package com.example.demo.controller;

import com.example.demo.entity.UserDTO;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Slf4j
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public RestEntity register(UserDTO userDTO){
        String username = userDTO.getUsername();
        String role = userDTO.getRole();
        UserDTO oldUser = this.userRepository.findOne(userDTO.getUsername());
        if (oldUser != null){
            log.error(RestStatusEnum.ACCOUNT_EXISTED.getMessage());
            return RestEntity.error(RestStatusEnum.ACCOUNT_EXISTED,null);
        }
        try {
            this.userRepository.save(userDTO);
        }catch (Exception e){
            log.error(RestStatusEnum.ACCOUNT_REGISTER_FAIL.getMessage());
            return RestEntity.error(RestStatusEnum.ACCOUNT_REGISTER_FAIL,e);
        }
        return RestEntity.ok(RestStatusEnum.ACCOUNT_REGISTER_SUCCESS,username,role);
    }

    @PostMapping("/login")
    public RestEntity login(UserDTO userDTO){
        UserDTO oldUser = this.userRepository.findOne(userDTO.getUsername());
        if (oldUser == null){
            log.error(RestStatusEnum.ACCOUNT_NOT_EXIST.getMessage());
            return RestEntity.error(RestStatusEnum.ACCOUNT_NOT_EXIST,null);
        }else {
            //密码对比
            String oldPassword = oldUser.getPassword();
            if (userDTO.getPassword().equals(oldPassword)){
                log.info(RestStatusEnum.ACCOUNT_LOGIN_SUCCESS.getMessage());
                String username = oldUser.getUsername();
                String role = oldUser.getRole();
                return RestEntity.ok(RestStatusEnum.ACCOUNT_LOGIN_SUCCESS,username,role);
            }else {
                log.error(RestStatusEnum.INVALID_CREDENTIAL.getMessage());
                return RestEntity.error(RestStatusEnum.INVALID_CREDENTIAL,null);
            }
        }
    }

    @GetMapping(value="/logout")
    public RestEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession();//获取当前session
        if (session != null) {
            session.invalidate();//关闭session
        }
        return RestEntity.ok(RestStatusEnum.ACCOUNT_LOGOUT_SUCCESS,null,null);
    }

    @GetMapping(value="/user")
    public RestEntity getUser() {
        List<UserDTO> userDTOS = this.userRepository.findAll();
        return RestEntity.ok(userDTOS);
    }

    /**
     * description: <br>
     * 根据用户名删除用户
     * @param: username
     * @return:
     * @method：
     */
    @DeleteMapping(value="/delUser/{username}")
    public RestEntity delUser(@PathVariable String username) {
        this.userRepository.delete(username);
        return RestEntity.ok(username);
    }
}
