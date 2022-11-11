package com.xwl.spring.controller;

import com.xwl.spring.domain.ToDoList;
import com.xwl.spring.server.ToDoListServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/ToDoList")
public class ToDoListController {
	@Autowired
	private ToDoListServer toDoListServer;

	@PostMapping
	//添加事项并返回所有数据
	public Result insertUser(String text){
		ToDoList toDoList1 = new ToDoList(text);
		//增加操作
		boolean flag = toDoListServer.insertUser(toDoList1);
		if (!flag){
			return new Result(Code.POST_ERR,flag);
		}
		//获取最新插入数据的主键
		int id = toDoList1.getId();
		//根据主键进行查询并返回
		ToDoList toDoList = toDoListServer.searchSingle(id);
		Integer code = toDoList != null?Code.GET_OK:Code.GET_ERR;
		String msg = toDoList == null? "查不到该id值的数据":"";
		System.out.println("insertData"+new Date());
		return new Result(code,toDoList,msg);
	}
	@DeleteMapping("/{id}")
	//删除事项
	public Result deleteUser(@PathVariable String id){
		boolean flag = toDoListServer.deleteUser(id);
		System.out.println("deleteData"+new Date());
		return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag);
	}
	@PutMapping("/{id}")
	//更改代办事项为已完成事项
	public Result upUser(@PathVariable String id){
		boolean flag = toDoListServer.upUser(id);
		System.out.println("upData"+new Date());
		return new Result(flag?Code.PUT_OK:Code.PUT_ERR,flag);
	}
	@GetMapping("/{state}")
	//分组查询 state 为 0 或 1的数据
	public Result searchByState(@PathVariable String state){
		List<ToDoList> toDoLists = toDoListServer.searchByState(state);
		Integer code = toDoLists == null?Code.GET_OK:Code.GET_ERR;
		String msg = toDoLists == null? "查不到该state值的数据":"";
		System.out.println(toDoLists);
		System.out.println("searchData"+new Date());
		return new Result(code,toDoLists,msg);
	}

}
