package org.yash.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.yash.messenger.model.Message;

public class MessageService {
	
	
	public List<Message> getAllMessages(){
		Message m1 = new Message(1L, "Hello", "yash");
		Message m2 = new Message(2L, "Yo", "yash");
		List<Message> messageList = new ArrayList<>();
		messageList.add(m1);
		messageList.add(m2);
		return messageList;
	}
	
	
}
