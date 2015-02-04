package co.insecurity.struts2ref.service;

import java.util.List;

import co.insecurity.struts2ref.model.PersonInfo;


public interface PersonService {

	PersonInfo getPerson(int id);
	
	void updatePerson(PersonInfo person);
	
	void savePerson(PersonInfo person);
	
	void deletePerson(int id);
	
	List<PersonInfo> getPersonList();
}
