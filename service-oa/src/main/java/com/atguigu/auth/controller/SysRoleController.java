package com.atguigu.auth.controller;

import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.execption.GuiguException;
import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.vo.system.SysRoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.jsonwebtoken.impl.crypto.RsaProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController  // 注册返回数据
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    // http://localhost:8800/admin/system/sysRole/findAll
    // 注入服务
    @Autowired
    private SysRoleService sysRoleService;

    // 查询所有的角色
//    @GetMapping("findAll")  // 路径起一个名字
//    public List<SysRole> findAll() {
//        // 调用service的方法
//        return sysRoleService.list();
//    }

    // 统一返回数据结果
    @ApiOperation("查询所有角色")
    @GetMapping("findAll")
    public Result<List<SysRole>> findAll(){

        // 模拟异常处理
        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new GuiguException(20001,"出现自定义异常");
        }
        int i = 10/0;
        List<SysRole> roleList = sysRoleService.list();
        return Result.ok(roleList);
    }

    //条件分页查询
    //page 当前页  limit 每页显示记录数
    //SysRoleQueryVo 条件对象
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo
                                ){
        //调用service的方法实
        //1 创建Page对象，传递分页相关参数
        //page 当前页  limit 每页显示记录数
        Page<SysRole> pageParam = new Page<>(page,limit);
        //2 封装条件，判断条件是否为空，不为空进行封装
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)) {
            //封装 like模糊查询
            wrapper.like(SysRole::getRoleName,roleName);
        }
        //3 调用方法实现
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    // 添加角色
    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role){
        // 调用service的方法添加
        boolean is_success = sysRoleService.save(role);
        if(is_success){
            return Result.ok();
        } else{
            return Result.fail();
        }
    }
    // 修改角色-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return Result.ok(role);
    }
    // 修改角色-最终修改
    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public Result update(@RequestBody SysRole role) {
        boolean is_success = sysRoleService.updateById(role);
        if(is_success){
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
    // 根据id删除
    @ApiOperation("根据id删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        boolean is_success = sysRoleService.removeById(id);
        if(is_success) {
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    // 批量删除
    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if(is_success) {
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
