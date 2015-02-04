package co.insecurity.struts2ref.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import co.insecurity.struts2ref.model.PersonInfo;


public class LocalPersonService implements PersonService {

	private static final Logger logger = Logger.getLogger(LocalPersonService.class.getName());
	
	private static SortedMap<Integer, PersonInfo> personMap = new TreeMap<Integer, PersonInfo>();
	private static List<PersonInfo> personList;
	static {
		personList = new ArrayList<PersonInfo>();
	}

	@Override
	public PersonInfo getPerson(int id) {
		return personMap.get(id);
	}

	@Override
	public void updatePerson(PersonInfo person) {
		personMap.put(person.getId(), person);
		rebuildPersonList();
	}

	@Override
	public void savePerson(PersonInfo person) {
		int newId = getNewId();
		person.setId(newId);
		personMap.put(newId, person);
		rebuildPersonList();
	}

	@Override
	public void deletePerson(int id) {
		personMap.remove(id);
		rebuildPersonList();
	}

	@Override
	public List<PersonInfo> getPersonList() {
		return LocalPersonService.personList;
	}

	private static int getNewId() {
		int newId = 0;
		try {
			newId = personMap.lastKey() + 1;
		} catch (NoSuchElementException e) {
			logger.info("id value set to 0");
		}
		return newId;
	}
	
	private static void rebuildPersonList() {
		personList = new ArrayList<PersonInfo>();
		for (Integer id : personMap.keySet()) {
			personList.add(personMap.get(id));
		}
	}
	
}
