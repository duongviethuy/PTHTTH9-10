import java.io.Serializable;

public class Employee implements Serializable {
	private int id;
	private String password;
	private int phone;
	private String name;
	private String email;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Employee(int id, String password, int phone, String name, String email, String description) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.email = email;
		this.description = description;
	}

	public Employee(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", password=" + password + ", phone=" + phone + ", name=" + name + ", email="
				+ email + ", description=" + description + "]";
	}

}
