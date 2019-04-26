package org.wolf.security4.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.wolf.security4.user.entity.SysUser;
import org.wolf.security4.user.service.ISysUserService;
import org.wolf.security4.user.service.impl.SysUserServiceImpl;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author luoqiz
 * @since 2019-04-25
 */
@RestController
@RequestMapping("/user/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/list")
    public List<SysUser> getUser() {
       return  sysUserService.list();
    }
}
