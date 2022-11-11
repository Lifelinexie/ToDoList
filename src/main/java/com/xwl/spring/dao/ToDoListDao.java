package com.xwl.spring.dao;

import com.xwl.spring.domain.ToDoList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface ToDoListDao {
	//添加事项
	@Insert("insert into test.todo_list VALUES (null,#{text},0)")
	@SelectKey(statement="select LAST_INSERT_ID()",keyProperty = "id",keyColumn = "id",before = false,resultType = int.class)
	void insertUser(ToDoList todolist);
	//删除事项
	@Delete("delete from test.todo_list where id = #{id}")
	void deleteUser(String id);
	//更改代办事项为已完成事项
	@Update("update test.todo_list set state = 1 where id = #{id}")
	void upUser(String id);
	//查询单条数据
	@Select("select * from test.todo_list where id = #{id}")
	ToDoList searchSingle(int id);
	//分组查询 state 为 0 或 1的数据
	@Select("select * from test.todo_list where state = #{state}")
	List<ToDoList> searchByState(String state);
}
