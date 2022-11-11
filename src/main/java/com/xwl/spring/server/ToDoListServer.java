package com.xwl.spring.server;

import com.xwl.spring.domain.ToDoList;
import java.util.List;

public interface ToDoListServer {

	//添加事项
	boolean insertUser(ToDoList todolist);

	//删除事项
	boolean deleteUser(String id);

	//更改代办事项为已完成事项
	boolean upUser(String id);

	//分组查询 state 为 0 或 1的数据
	List<ToDoList> searchByState(String state);

	//通过主键查询单条数据
	ToDoList searchSingle(int id);
}
