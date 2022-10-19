import java.util.List;
import java.util.Scanner;

import controller.StudentHelper;
import model.StudentRecordProgram;

public class StudentRecordsMenu {

		static Scanner in = new Scanner(System.in);
		static StudentHelper sHelper = new StudentHelper();

		private static void addStudent() {
		
			System.out.print("Enter the student's GPA: ");
			String GPA = in.nextLine();
			
			System.out.print("Enter the Student's name: ");
			String student = in.nextLine();
			
			StudentRecordProgram toAdd = new StudentRecordProgram(GPA, student);
			sHelper.insertStudent(toAdd);

		}

		private static void deleteStudent() {
			
			System.out.print("Enter the GPA to delete: ");
			String GPA = in.nextLine();
			
			System.out.print("Enter the student's name to delete: ");
			String student = in.nextLine();
			
			StudentRecordProgram toDelete = new StudentRecordProgram(GPA, student);
			sHelper.deleteStudent(toDelete);

		}

		private static void editStudent() {
			
			System.out.println("Please choose a search method: ");
			System.out.println("1. Search by GPA");
			System.out.println("2. Search by Student");
			int searchBy = in.nextInt();
			in.nextLine();
			
			List<StudentRecordProgram> foundStudent;
			if (searchBy == 1) {
				System.out.print("Enter the GPA: ");
				String GPAScore = in.nextLine();
				foundStudent = sHelper.searchStudentByGPA(GPAScore);
			} else {
				System.out.print("Enter the student's name");
				String studentName = in.nextLine();
				foundStudent = sHelper.searchStudentByName(studentName);


			}

			if (!foundStudent.isEmpty()) {
				System.out.println("Found");
				for (StudentRecordProgram l : foundStudent) {
					System.out.println(l.getId() + " : " + l.toString());
				}
				System.out.print("Which student ID would you like to edit? ");
				int editThisID = in.nextInt();

				StudentRecordProgram toEdit = sHelper.searchForStudentById(editThisID);
				System.out.println("Got " + toEdit.getStudent() + " from " + toEdit.getGPA());
				System.out.println("1. Update GPA Score");
				System.out.println("2. Update Student Name");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New GPA Score: ");
					String newGPA = in.nextLine();
					toEdit.setGPA(newGPA);
				} else if (update == 2) {
					System.out.print("New Student Name: ");
					String newStudent = in.nextLine();
					toEdit.setStudent(newStudent);
				}

				sHelper.updateStudent(toEdit);

			} else {
				System.out.println("Nothing Found!");
			}

		}

		public static void main(String[] args) {
			
			showMenu();

		}

		public static void showMenu() {
			boolean displayAgain = true;
			System.out.println("-  Student Records Program  - ");
			while (displayAgain) {
				System.out.println("-  Select an option from the menu:");
				System.out.println("  ");
				System.out.println("  1. Add a student");
				System.out.println("  2. Edit a student");
				System.out.println("  3. Delete a student");
				System.out.println("  4. View student list");
				System.out.println("  5. Exit program");
				System.out.println("  ");
				System.out.print("-  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addStudent();
				} else if (selection == 2) {
					editStudent();
				} else if (selection == 3) {
					deleteStudent();
				} else if (selection == 4) {
					ListStudents();
				} else {
					sHelper.cleanUp();
					System.out.println("------------ Program Ended -------------");
					displayAgain = false;
				}

			}

		}

		private static void ListStudents() {
			
			List<StudentRecordProgram> allStudents = sHelper.showAllStudents();
			
			for(StudentRecordProgram singleStudent: allStudents) {
				
				System.out.println(singleStudent.returnStudentDetails());
			}

		}

	}