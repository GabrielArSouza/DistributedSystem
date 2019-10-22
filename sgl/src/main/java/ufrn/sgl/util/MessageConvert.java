package ufrn.sgl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import ufrn.sgl.messages.Message;

public class MessageConvert {

	private static MessageConvert instance = null;
	
	private MessageConvert () {}
	
	public static synchronized MessageConvert getInstance () {
		if (instance == null) 
			instance = new MessageConvert();
		return instance;
	}
	
	public Message convertByteArrayToMessage (byte[] array) {
		ObjectInputStream iStream;
		try {
			iStream = new ObjectInputStream(new ByteArrayInputStream(array));
			Message msg = (Message) iStream.readObject();
    		iStream.close();
    		return msg;
		}catch (Exception e) {
			System.out.println("Error: Convert byte array in message");
			return null;
		}

	}
	
	public byte[] convertMessageToByteArray (Message obj) {
		
		try {
	
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bStream);
			oo.writeObject(obj);
			oo.flush();
			
			// get the byte array of the object
			byte[] byteMessage = bStream.toByteArray();
			
			oo.close();
			
			return byteMessage;
		
		} catch (IOException e) {
			System.out.println("ERROR - could not serialize object");
			e.printStackTrace();
		} 
		
		return null;
	}
	
}
