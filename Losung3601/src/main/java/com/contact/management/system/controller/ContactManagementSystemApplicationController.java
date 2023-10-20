package com.contact.management.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contact.management.system.entity.Contacts;
import com.contact.management.system.model.ContactsDto;
import com.contact.management.system.response.Response;
import com.contact.management.system.service.ContactService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/contacts")
public class ContactManagementSystemApplicationController {

	@Autowired
	ContactService contactService;
	
	@GetMapping("/getAllContact")
	public Response getContact(HttpServletRequest request) {
		Response resp = contactService.fetchAllContacts();
		return resp;
	}
	
	@PostMapping("/createContact")
	public Response createContact(HttpServletRequest request,@RequestBody ContactsDto contactsDto) {
		Response resp = contactService.saveContact(contactsDto);
		return resp;
	}
	
	@PutMapping("/updateContact")
	public Response updateContact(HttpServletRequest request,@RequestBody ContactsDto contactsDto) {
		Response resp = contactService.updateContact(contactsDto, contactsDto.getId());
		return resp;
	}
	
	@DeleteMapping("/deleteContact/{contactId}")
	public Response deleteContact(HttpServletRequest request,@PathVariable(value = "contactId") Integer contactId) {
		Response resp = contactService.deleteContact(contactId);
		return resp;
	}
	
	@DeleteMapping("/deleteMultipleContacts")
	public  List<Response> deleteMultipleContact(HttpServletRequest request,@RequestHeader(value = "contactIdsList") List<Integer> contactIdsList) {
		 List<Response> resp = contactService.deleteContactByIds(contactIdsList);
		return resp;
	}
	
	@PostMapping("/createListOfContacts")
	public List<Response> createListOfContact(HttpServletRequest request,@RequestBody List<ContactsDto> contactsDto) {
		List<Response> resp = contactService.saveListOfContacts(contactsDto);
		return resp;
	}
	

	@PutMapping("/updateListOfContacts")
	public List<Response> updateListOfContacts(HttpServletRequest request,@RequestBody List<Contacts> contactsDto) {
		List<Response> resp = contactService.updateListOfContacts(contactsDto);
		return resp;
	}
		
	@GetMapping("/findByFirstName/{firstName}")
	public Response findByFirstName(HttpServletRequest request,@PathVariable(value = "firstName") String firstName) {
		Response resp = contactService.findByFirstName(firstName);
		return resp;
	}
	@GetMapping("/findByLastName/{lastName}")
	public Response findByLastName(HttpServletRequest request,@PathVariable(value = "lastName") String lastName) {
		Response resp = contactService.findByLastName(lastName);
		return resp;
	}
	
	@GetMapping("/findByEmail/{email}")
	public Response findByEmail(HttpServletRequest request,@PathVariable(value = "email") String email) {
		Response resp = contactService.findByEmail(email);
		return resp;
	}
	
	@GetMapping("/findByPhoneNumber/{phoneNo}")
	public Response findByPhoneNumber(HttpServletRequest request,@PathVariable(value = "phoneNo") String phoneNo) {
		Response resp = contactService.findByPhoneNumber(phoneNo);
		return resp;
	}
}
