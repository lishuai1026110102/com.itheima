package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;

import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.web.bind.annotation.*;
import com.itheima.health.entity.Result;


import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll() {
        // 调用服务查询所有的检查项
        List<CheckItem> list = checkItemService.findAll();
        // 封装返回的结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);

    }

    //添加检查项
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        //调用服务添加
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //分页查询
    @PostMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        //return  pageResult
        //返回给页面 包装到Result
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }


    //根据id删除检查项
    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        try {
            checkItemService.deleteById(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }

    }

    @GetMapping("/findById")
    public Result findById(int id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);

    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem) {
        //调用服务更新
        checkItemService.update(checkItem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

}
