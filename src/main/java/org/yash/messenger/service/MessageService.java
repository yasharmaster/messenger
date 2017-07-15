package org.yash.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yash.messenger.database.DatabaseClass;
import org.yash.messenger.model.Message;
import org.yash.messenger.model.Profile;

public class MessageService {
	
	private static Map<Long, Message> messages = DatabaseClass.getMessages();
	
	private static long counter = 0;
	
	static {
		long id = ++counter;
		messages.put(id, new Message(id, "Hello", "yash"));
		id = ++counter;
		messages.put(id, new Message(id, "Yo", "yash"));
	}
	
	public MessageService(){

	}
	
	public List<Message> getAllMessages(){

		return new ArrayList<>(messages.values());
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message addMessage(Message message){
		message.setId(++counter);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message){
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
	
	
}
