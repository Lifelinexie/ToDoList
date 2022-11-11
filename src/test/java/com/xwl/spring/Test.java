package com.xwl.spring;

import com.xwl.spring.config.SpringConfig;
import com.xwl.spring.domain.ToDoList;
import com.xwl.spring.server.ToDoListServer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class Test {
	@Autowired
	private ToDoListServer toDoListServer;
	@org.junit.Test
	public void TestSearch(){
		List<ToDoList> list = toDoListServer.searchByState("0");
		System.out.println(list.get(0).toString());
	}
	@org.junit.Test
	public void TestAdd(){

		System.out.println(toDoListServer.insertUser(new ToDoList("今天吃什么")));
	}


}
