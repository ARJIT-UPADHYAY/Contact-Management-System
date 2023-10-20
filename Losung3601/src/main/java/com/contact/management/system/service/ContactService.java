package com.contact.management.system.service;

import java.util.List;

import com.contact.management.system.entity.Contacts;
import com.contact.management.system.model.ContactsDto;
import com.contact.management.system.response.Response;

public interface ContactService {

	Response saveContact(ContactsDto contacts);
	
    Response fetchAllContacts();
    
    Response updateContact(ContactsDto contacts, Integer contactId);
    
    Response deleteContact(Integer contactId);
    	
    Response findByFirstName(String firstName);
    
    Response findByLastName(String lastName);
    
    Response findByEmail(String email);
    
    Response findByPhoneNumber(String phoneNumber);
    
    List<Response> saveListOfContacts(List<ContactsDto> contacts);
    
    List<Response> updateListOfContacts(List<Contacts> contacts);
    
    List<Response> deleteContactByIds(List<Integer> contactIds);
    
    
	
	
}
