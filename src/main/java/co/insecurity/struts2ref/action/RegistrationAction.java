package co.insecurity.struts2ref.action;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import co.insecurity.struts2ref.model.PersonInfo;
import co.insecurity.struts2ref.service.LocalPersonService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;

import org.apache.log4j.Logger;


public class RegistrationAction extends ActionSupport implements 
	ServletRequestAware,
	SessionAware, 
	ParameterNameAware, 
	Preparable {

	private static final Logger logger = 
		Logger.getLogger(RegistrationAction.class.getName());
	private static final long serialVersionUID = 1L;
	
	// Session attributes
	private static final String USER_FNAME = "userFName";

	private HttpServletRequest request;
	private Map<String, Object> userSession;
	private PersonInfo person;
	private int id;
	private String[] allowedGenders;
	private List<PersonInfo> personList;
	private LocalPersonService personService;

	
	public void printState() {
		String state = "";
		state += "id: " + id;
		if (person != null)
			state += "\nPerson: " + person.toString();
		if (personList != null) {
			state += "\nPerson List:";
			for (PersonInfo p : personList)
				state += "\n" + p.getId() + " - " + p.getFirstName();
		}
		if (userSession != null) {
			state += "\nSession:";
			for (String key : userSession.keySet())
				state += "\n" + key + ":" + userSession.get(key).toString();
		}
		System.out.println(state);
	}
	
	public void prepare() throws Exception {
		logger.info("In prepare method");
		System.out.println("-------------------------------------------------------------------");
		String requestURI = request.getRequestURI();
		System.out.println("Request URI: " + requestURI);
		System.out.println("context: " + request.getContextPath());
		System.out.println("pathinfo: " + request.getPathInfo());
		System.out.println("pathtranslated: " + request.getPathTranslated());
		System.out.println("requrl: " + request.getRequestURL());
		System.out.println("servletpath: " + request.getServletPath());
		System.out.println("-------------------------------------------------------------------");
		allowedGenders = PersonInfo.getAllowedGenders();
	}
	
	public void validate() {
		logger.info("In validate method");
		if (person.getGender() == null || !Arrays.asList(allowedGenders).contains(person.getGender()))
			addFieldError("person.gender", "Gender cannot be left blank.");
	}
	
	public String input() {
		logger.info("In input method");
		return INPUT;
	}
	
	public String execute() throws Exception {
		logger.info("In execute method");
		personList = personService.getPersonList();
		return SUCCESS;
	}
	
	public String create() {
		logger.info("In create method");
		setPerson(new PersonInfo());
		return INPUT;
	}
	
	public String edit() {
		logger.info("In edit method");
		setPerson(personService.getPerson(id));
		return INPUT;
	}

	public String saveOrUpdate() {
		logger.info("In saveOrUpdate method");
		if (person.getId() > -1) 
			personService.updatePerson(person);
		else
			personService.savePerson(person);
		personList = personService.getPersonList();
		userSession.put(USER_FNAME, person.getFirstName());
		return SUCCESS;
	}

	public String delete() {
		personService.deletePerson(id);
		personList = personService.getPersonList();
		return SUCCESS;
	}

	public PersonInfo getPerson() {
		return person;
	}

	public void setPerson(PersonInfo person) {
		this.person = person;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getAllowedGenders() {
		return Arrays.asList(allowedGenders);
	}
	
	public List<PersonInfo> getPersonList() {
		return personList;
	}

	public void setPersonList(List<PersonInfo> personList) {
		this.personList = personList;
	}

	public LocalPersonService getPersonService() {
		return personService;
	}

	public void setPersonService(LocalPersonService personService) {
		this.personService = personService;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void setSession(Map<String, Object> session) {
		userSession = session;
	}
	
	public boolean acceptableParameterName(String parameterName) {
		boolean allowedParameterName = true;
		if (parameterName.contains("session") || parameterName.contains("request")) {
			allowedParameterName = false;
		}
		return allowedParameterName;
	}
}
