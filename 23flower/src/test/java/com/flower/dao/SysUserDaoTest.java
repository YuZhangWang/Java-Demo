package com.flower.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import com.flower.dao.SysUserDao;
import com.flower.entity.SysUser;

import java.util.List;

/**
 * Created by yumaoying on 2018/3/25.
 * UserInfoDao的测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserDaoTest {
    @Resource
    private SysUserDao sysUserDao;

    @Test
    public void test() {
        List<SysUser> sysUserList = sysUserDao.findAll();
        for (SysUser sysUser : sysUserList) {
            System.out.println("=========================================" + sysUser);
        }
    }
}
