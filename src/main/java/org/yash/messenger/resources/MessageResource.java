package org.yash.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.yash.messenger.model.Message;
import org.yash.messenger.resources.beans.MessageFilterBean;
import org.yash.messenger.service.MessageService;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON) 
public class MessageResource {

	MessageService messageService = new MessageService();
	
    @GET
	public List<Message> getMessages(@BeanParam MessageFilterBean bean) {
    	if (bean.getYear() > 0) {
    		return messageService.getAllMessagesForYear(bean.getYear());
    	}
    	if (bean.getStart() >= 0 && bean.getSize() > 0) {
    		return messageService.getAllMessagesPaginated(bean.getStart(), bean.getSize());
    	}
		return messageService.getAllMessages();
	}
   
    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        String newMessageId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder()
                        .path(newMessageId)
                        .build();
        return Response.created(uri)
                        .entity(newMessage)
                        .build();
    }
    
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long messageId, Message message) {
    	message.setId(messageId);
    	return messageService.updateMessage(message);
    }
    
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long messageId) {
    	messageService.removeMessage(messageId);
    }
    
    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId) {
    	return messageService.getMessage(messageId);
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
    	return new CommentResource();
    }
}
