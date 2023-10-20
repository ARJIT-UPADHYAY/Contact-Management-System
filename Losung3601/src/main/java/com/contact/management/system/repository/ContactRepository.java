package com.contact.management.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contact.management.system.entity.Contacts;

@Repository
public interface ContactRepository extends JpaRepository<Contacts, Integer>{

	List<Contacts> findByFirstName(String firstName);
	
	List<Contacts> findByLastName(String lastName);
	
	List<Contacts> findByEmail(String email);
	
	List<Contacts> findByPhoneNumber(String phoneNumber);
	
}
