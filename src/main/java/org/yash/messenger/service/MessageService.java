package org.yash.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.yash.messenger.database.DatabaseClass;
import org.yash.messenger.exception.DataNotFoundException;
import org.yash.messenger.model.Message;

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
	
	public List<Message> getAllMessagesForYear(int year){
		List<Message> messageList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message msg : messages.values()) {
			cal.setTime(msg.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageList.add(msg);
			}
		}
		return messageList;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
			ArrayList<Message> list = new ArrayList<>(messages.values());
			if (start + size > list.size()) return new ArrayList<Message>();
			return list.subList(start, start+size);
	}
	
	public Message getMessage(long id){
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Messge with id " + id + " not found.");
		}
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId(++counter);
		messages.put(message.getId(), new Message(message.getId(), message.getMessage(), message.getAuthor()));
		return messages.get(message.getId());
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
