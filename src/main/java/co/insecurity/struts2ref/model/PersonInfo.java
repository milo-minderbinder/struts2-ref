package co.insecurity.struts2ref.model;

public class PersonInfo {

	private static final String[] ALLOWED_GENDERS = { "male", "female", "unspecified" };
	
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private int age;
	private String email;
	private String residency;
	private boolean over21;
	private String sport;
	private String[] carModels;


	public PersonInfo() {
		this.id = -1;
		this.firstName = null;
		this.lastName = null;
		this.gender = null;
		this.age = -1;
		this.email = null;
		this.residency = null;
		this.over21 = false;
		this.sport = null;
		this.carModels = null;
	}

	public PersonInfo(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static String[] getAllowedGenders() {
		return PersonInfo.ALLOWED_GENDERS;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return this.age;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}

	public String getResidency() {
		return residency;
	}
	
	public void setOver21(boolean over21) {
		this.over21 = over21;
	}

	public boolean isOver21() {
		return over21;
	}

	public void setCarModels(String[] carModels) {
		this.carModels = carModels;
	}

	public String[] getCarModels() {
		return carModels;
	}
	
	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getSport() {
		return sport;
	}

	public String toString() {
		return "First Name: " + getFirstName() + " | " + 
			"\tLast Name: " + getLastName() + " | " +
			"\tGender: " + getGender() + " | " +
			"\tOver 21: " + isOver21() + " | " +
			"\tEmail: " + getEmail();/* + " | " +
			"\tAge: " + getAge() + " | " +
			"\tResidency: " + getResidency() + " | " +
			"\tCar Models: " + Arrays.asList( getCarModels() ) + " | " +
			"\tFavorite Sport: " + getSport();*/
	}
}
