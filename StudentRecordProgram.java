package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student_info")
public class StudentRecordProgram {
@Id
@GeneratedValue
@Column(name="ID")
private int id;
@Column(name="GPA")
private String GPA;
@Column(name="studentName")
private String student;
public StudentRecordProgram(){
super();
}
public StudentRecordProgram(String GPA, String student){
super();
this.GPA = GPA;
this.student = student;
}
public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}
public String getGPA() { 
return GPA;
}
public void setGPA(String GPA) {
this.GPA = GPA;
}
public String getStudent() {
return student;
}
public void setStudent(String student) {
this.student = student;
}
public String returnStudentDetails( )
{
return this.GPA + ": " + this.student;
}
}