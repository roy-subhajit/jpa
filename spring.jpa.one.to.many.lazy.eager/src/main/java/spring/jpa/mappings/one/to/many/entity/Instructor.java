package spring.jpa.mappings.one.to.many.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	//Special instruction here about the reference table
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	//Instructor can have multiple courses. As per requirement not to delete course
	//<NEW> Change FetchType to EAGER, for a quick solution of fetchInstructorById(..)
	@OneToMany(mappedBy = "instructor",
			   //fetch = FetchType.EAGER,
			   cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Course> courses;

	public Instructor() {}

	public Instructor(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	
	//<NEW> Setup the bi-directnal data. Add the course and set the Instructor on the fly.
	public void addCourse(Course course) {
		if(courses == null)
			courses = new ArrayList<Course>();
		
		courses.add(course);
		course.setInstructor(this);
	}
}
