package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.StudentRecordProgram;

public class StudentHelper {
	static EntityManagerFactory eMenager = Persistence.createEntityManagerFactory("StudentProject");
	
	public void insertStudent(StudentRecordProgram li) {
		EntityManager entityM = eMenager.createEntityManager();
		entityM.getTransaction().begin();
		entityM.persist(li);
		entityM.getTransaction().commit();
		entityM.close();
		
	}
	
	public List<StudentRecordProgram> showAllStudents(){
		EntityManager em = eMenager.createEntityManager();
		List<StudentRecordProgram> allStudents = em.createQuery("SELECT i from StudentRecordProgram i").getResultList();
		return allStudents;
		
	}

	public void deleteStudent(StudentRecordProgram forDeletion) {
		EntityManager em = eMenager.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentRecordProgram> myQuery = em.createQuery("select li from StudentRecordProgram li where li.GPA = :selectedGPA and li.student = :selectedStudent", StudentRecordProgram.class);
		
		myQuery.setParameter("selectedGPA", forDeletion.getGPA());
		myQuery.setParameter("selectedStudent", forDeletion.getStudent());
		
		myQuery.setMaxResults(1);
		
		StudentRecordProgram result = myQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public StudentRecordProgram searchForStudentById(int idToEdit) {
		
		EntityManager em = eMenager.createEntityManager();
		em.getTransaction().begin();
		
		StudentRecordProgram found = em.find(StudentRecordProgram.class, idToEdit);
		em.close();
		return found;
	}

	public void updateStudent(StudentRecordProgram toEdit) {
		
		EntityManager entityM = eMenager.createEntityManager();
		entityM.getTransaction().begin();
		
		entityM.merge(toEdit);
		entityM.getTransaction().commit();
		entityM.close();
	}

	public List<StudentRecordProgram> searchStudentByGPA(String GPANum) {
		
		EntityManager em = eMenager.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentRecordProgram> typedQuery = em.createQuery("select li from StudentRecordProgram li where li.GPA = :selectedGPA", StudentRecordProgram.class);
		
		typedQuery.setParameter("selectedGPA", GPANum);
		
		List<StudentRecordProgram> foundStudents = typedQuery.getResultList();
		em.close();
		return foundStudents;
	}

	public List<StudentRecordProgram> searchStudentByName(String studentName) {
		
		EntityManager em = eMenager.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StudentRecordProgram> typedQuery = em.createQuery("select li from StudentRecordProgram li where li.student = :selectedStudent", StudentRecordProgram.class);
		
		typedQuery.setParameter("selectedStudent", studentName);
		
		List<StudentRecordProgram> foundStudents = typedQuery.getResultList();
		em.close();
		return foundStudents;
	}
	
	public void cleanUp() {
		eMenager.close();
	}
	
}