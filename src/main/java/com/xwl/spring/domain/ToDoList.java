package com.xwl.spring.domain;

public class ToDoList {

	private int id;
	private String text;
	private int state;

	@Override
	public String toString() {
		return "ToDoList{" +
			"id=" + id +
			", text='" + text + '\'' +
			", state=" + state +
			'}';
	}

	public ToDoList(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
