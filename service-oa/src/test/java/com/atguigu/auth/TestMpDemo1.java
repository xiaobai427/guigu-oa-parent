package com.atguigu.auth;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    // 注入
    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void getAll(){
        List<SysRole> list = mapper.selectList(null);
        System.out.println(list);
    }

    // 添加操作
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员1");
        sysRole.setRoleCode("role1");
        sysRole.setDescription("角色管理员1");
        int result = mapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole.getId()); //id自动回填
    }

    // 修改操作
    @Test
    public void update(){
        // 根据id查询
        SysRole sysRole = mapper.selectById(10);
        // 设置修改值
        sysRole.setRoleName("atguigu角色管理员");
        // 调用方法实现最终修改
        int result = mapper.updateById(sysRole);
        System.out.println(result);
        System.out.println(sysRole.getId());
    }

    // 删除操作
    @Test
    public void deleteId(){
        int result = mapper.deleteById(10);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = mapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }
}
