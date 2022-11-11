package com.xwl.spring.server.Impl;

import com.xwl.spring.dao.ToDoListDao;
import com.xwl.spring.domain.ToDoList;
import com.xwl.spring.server.ToDoListServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ToDoListServerImpl implements ToDoListServer {
	@Autowired
	private ToDoListDao toDoListDao;

	@Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean insertUser(ToDoList todolist) {
		toDoListDao.insertUser(todolist);
		return true;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean deleteUser(String id) {
		toDoListDao.deleteUser(id);
		return true;
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean upUser(String id) {
		toDoListDao.upUser(id);
		return true;
	}
	@Override
	public List<ToDoList> searchByState(String state) {

		return toDoListDao.searchByState(state);
	}
	@Override
	public ToDoList searchSingle(int id) {
		return toDoListDao.searchSingle(id);
	}
}
