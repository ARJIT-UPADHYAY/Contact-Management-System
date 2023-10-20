package com.contact.management.system.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.contact.management.system.entity.Contacts;
import com.contact.management.system.model.ContactsDto;
import com.contact.management.system.repository.ContactRepository;
import com.contact.management.system.response.Response;
import com.contact.management.system.service.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Response saveContact(ContactsDto contacts) {
		Response resp = new Response();
		try {
			Contacts entity = this.modelMapper.map(contacts, Contacts.class);
			Contacts output = contactRepository.save(entity);

			List<Contacts> list = new ArrayList<>();
			list.add(output);
			resp.setData(list);
			resp.setDescription("Contact Saved successfully");
			resp.setStatus("Success");

		} catch (Exception e) {

			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);

		}
		return resp;
	}

	@Override
	public Response fetchAllContacts() {

		Response resp = new Response();
		try {
			Iterable<Contacts> data = contactRepository.findAll();
			List<Contacts> result = new ArrayList<Contacts>();

			// void java.lang.Iterable.forEach(Consumer<? super Contacts> action)
			// adding data from Iterable data to Collection List
			data.forEach(result::add);

			resp.setData(result);
			resp.setDescription("Fetched data successfully");
			resp.setStatus("Success");

		} catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response updateContact(ContactsDto contacts, Integer contactId) {
		Response resp = new Response();
		List<Contacts> result = new ArrayList<Contacts>();

		try {
			Contacts contDB = contactRepository.findById(contactId).get();

			if (!StringUtils.isEmpty(contacts.getEmail())) {
				contDB.setEmail(contacts.getEmail());

			}
			if (!StringUtils.isEmpty(contacts.getFirstName())) {
				contDB.setEmail(contacts.getFirstName());

			}
			if (!StringUtils.isEmpty(contacts.getLastName())) {
				contDB.setEmail(contacts.getLastName());

			}
			if (!StringUtils.isEmpty(contacts.getPhoneNumber())) {
				contDB.setEmail(contacts.getPhoneNumber());

			}

			Contacts output = contactRepository.save(contDB);
			resp.setStatus("Success");
			resp.setDescription("Contact updated Suceessfully");
			result.add(output);
			resp.setData(result);

		} catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response deleteContact(Integer contactId) {
		Response resp = new Response();

		try {
			contactRepository.deleteById(contactId);
			resp.setStatus("Success");
			resp.setDescription("Contact deleted Suceessfully");
			resp.setData(null);

		} catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response findByFirstName(String firstName) {
		Response resp = new Response();

		try {

			List<Contacts> list = contactRepository.findByFirstName(firstName);
			if(list!=null && !list.isEmpty())
			{
			resp.setStatus("Success");
			resp.setDescription("Fetched by firstname");
			resp.setData(list);
			}
			else
			{
				resp.setStatus("Success");
				resp.setDescription("No data Found");
				resp.setData(null);
			}

		}

		catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response findByLastName(String lastName) {
		Response resp = new Response();

		try {

			List<Contacts> list = contactRepository.findByLastName(lastName);
			if(list!=null && !list.isEmpty())
			{
			resp.setStatus("Success");
			resp.setDescription("Fetched by lastName");
			resp.setData(list);
			}
			else
			{
				resp.setStatus("Success");
				resp.setDescription("No data Found");
				resp.setData(null);
			}

		}

		catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response findByEmail(String email) {
		Response resp = new Response();

		try {

			List<Contacts> list = contactRepository.findByEmail(email);
			if(list!=null && !list.isEmpty())
			{
			resp.setStatus("Success");
			resp.setDescription("Fetched by email");
			resp.setData(list);
			}
			else
			{
				resp.setStatus("Success");
				resp.setDescription("No data Found");
				resp.setData(null);
			}

		}

		catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public Response findByPhoneNumber(String phoneNumber) {
		Response resp = new Response();

		try {

			List<Contacts> list = contactRepository.findByPhoneNumber(phoneNumber);
			if(list!=null && !list.isEmpty())
			{
			resp.setStatus("Success");
			resp.setDescription("Fetched by phoneNumber");
			resp.setData(list);
			}
			else
			{
				resp.setStatus("Success");
				resp.setDescription("No data Found");
				resp.setData(null);
			}

		}

		catch (Exception e) {
			resp.setStatus("Failed");
			resp.setDescription(e.getMessage());
			resp.setData(null);
		}
		return resp;
	}

	@Override
	public List<Response> saveListOfContacts(List<ContactsDto> contacts) {
		List<Response> resp = new ArrayList();
		
		Contacts entity = null;

		for (ContactsDto contactObject : contacts) {
			Response obj=new Response();
			List<Contacts> list = new ArrayList<>();
			try {
				entity = this.modelMapper.map(contactObject, Contacts.class);
				Contacts output = contactRepository.save(entity);

				list.add(output);
				obj.setData(list);
				obj.setDescription("Contact with FirstName= " + entity.getFirstName() + " Saved successfully");
				obj.setStatus("Success");

			} catch (Exception e) {

				obj.setStatus("Failed to save contact with FirstName =" + contactObject.getFirstName());
				obj.setDescription(e.getMessage());
				list.add(entity);
				obj.setData(list);

			}
			resp.add(obj);
			
		}
		return resp;
	}

	@Override
	public List<Response> updateListOfContacts(List<Contacts> contactslist) {
		List<Response> resp = new ArrayList<>();
		Contacts contDB = null;
		
		for (Contacts contacts : contactslist) {
			Response obj=new Response();
			try {
				List<Contacts> result = new ArrayList<Contacts>();
				contDB = contactRepository.findById(contacts.getId()).get();

				if (!StringUtils.isEmpty(contacts.getEmail())) {
					contDB.setEmail(contacts.getEmail());

				}
				if (!StringUtils.isEmpty(contacts.getFirstName())) {
					contDB.setFirstName(contacts.getFirstName());

				}
				if (!StringUtils.isEmpty(contacts.getLastName())) {
					contDB.setLastName(contacts.getLastName());

				}
				if (!StringUtils.isEmpty(contacts.getPhoneNumber())) {
					contDB.setPhoneNumber(contacts.getPhoneNumber());

				}

				Contacts output = contactRepository.save(contDB);
				obj.setStatus("Success");
				obj.setDescription("Contact updated Suceessfully for id = " + contacts.getId());
				result.add(output);
				obj.setData(result);

			} catch (Exception e) {
				obj.setStatus("Failed to update for id = " + contacts.getId());
				obj.setDescription(e.getMessage());
				obj.setData(null);
			}
			resp.add(obj);
		}
		return resp;
	}

	@Override
	public List<Response> deleteContactByIds(List<Integer> contactIds) {
		 List<Response> resp = new ArrayList<>();
		for (Integer id : contactIds) {
			Response obj=new Response();
			try {
				contactRepository.deleteById(id);
				obj.setStatus("Success");
				obj.setDescription("Contact deleted Suceessfully for id = " + id);
				obj.setData(null);

			} catch (Exception e) {
				obj.setStatus("Failed to delete for id = " + id);
				obj.setDescription(e.getMessage());
				obj.setData(null);
			}
			resp.add(obj);

		}
		return resp;
	}

}
