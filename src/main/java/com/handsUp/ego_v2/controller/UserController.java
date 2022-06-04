package com.handsUp.ego_v2.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.handsUp.ego_v2.common.BaseContext;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.dto.UserDto;
import com.handsUp.ego_v2.entity.User;
import com.handsUp.ego_v2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/9
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){
        log.info("用户 "+user.getUserName()+" 登陆");
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,user.getUserName());
        User user1 = userService.getOne(wrapper);

        if(user1 == null){
            return R.error("登录失败，不存在该用户");
        }

        if(!user1.getPassword().equals(password)){
            return R.error("用户密码错误");
        }

        if(user1.getIsBanned() == 1){
            return R.error("该账户已被禁用");
        }

        log.info("登陆成功");
        BaseContext.setCurrentId(user1.getId());
        request.getSession().setAttribute("user",user1.getId());

        return R.success(user1);
    }

    @PostMapping("/logout")
    public R<String> logOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        log.info("注销成功");
        return R.success("注销成功");
    }

    /**
     * 注册接口
     * @param user
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody User user){
        log.info("新用户注册 {}",user.toString());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User :: getUserName,user.getUserName());
        User user1 = userService.getOne(wrapper);
        if(user1 != null) return R.error("该用户已经存在");

        String pwd = DigestUtils.md5DigestAsHex("123456".getBytes());
        if(user.getPassword() != null) pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        user.setPassword(pwd);
        user.setIsBanned(0);

        userService.save(user);
        return R.success("用户注册成功！");
    }
    @GetMapping("/info/{id}")
    public R<User> getUserInfo(@PathVariable Long id){
        log.info("get user info");
        User user = userService.getById(id);
        return R.success(user);
    }

    @GetMapping("/{id}")
    public R<UserDto> get(@PathVariable Long id){
        log.info("getting user by id ...");
        UserDto userDto = userService.getInfo(id);
        return R.success(userDto);
    }

    /**
     * 更新用户接口
     * @param user
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody User user){
        log.info("update user...");
        System.out.println(user);
        try {
            userService.updateById(user);
        }catch (Exception e){
            e.printStackTrace();
            return R.error("update failed");
        }

        return R.success("update success...");
    }
}
