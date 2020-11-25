package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.CheckGroup;

import com.itheima.health.service.CheckGroupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.health.entity.Result;





@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;


     // 添加检查组

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        // 调用业务服务
        checkGroupService.add(checkGroup, checkitemIds);
        // 响应结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

//分页查看
    @PostMapping("/findPage")
    public  Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult <CheckGroup> pageRsult=checkGroupService.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageRsult);

    }

    // sha删除检查组
    @PostMapping("/deleteById")
    public  Result  deleteById(int id ){


        checkGroupService.deleteById(id);



        return  new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}