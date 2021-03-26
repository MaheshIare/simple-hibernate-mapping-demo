/**
 * 
 */
package com.hibernate.mapping.demo;


import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.mapping.demo.entity.Course;
import com.hibernate.mapping.demo.entity.Institute;
import com.hibernate.mapping.demo.entity.Student;
import com.hibernate.mapping.demo.util.HibernateUtil;


/**
 * @author mahes
 *
 */
public class HibernateMappingDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try{
		//Get Session
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		
		//start transaction
		tx = session.beginTransaction();
		Institute instituteData = buildInstituteData();
		
		session.saveOrUpdate(instituteData);
		//Commit transaction
		tx.commit();
		
		
		}catch(Exception e){
			System.out.println("Exception occured. "+e.getMessage());
			e.printStackTrace();
		}finally{
			if(!sessionFactory.isClosed()){
				System.out.println("Closing SessionFactory");
				sessionFactory.close();
			}
		}
	}

	private static Institute buildInstituteData() {
		Institute institute = new Institute();
		institute.setInstitudeCode("IARE");
		institute.setInstituteName("Institute of Aeronautical Engineering");
		
		Course courses = new Course("Computer Science Engineering", "CSE", institute);
		Course coursesTwo = new Course("Electronics Electrical Engineering", "EEE", institute);
		
		Set<Student> students = new HashSet<>();
		Student studentOne = new Student("Mahesh Kumar", "Gutam", "mahesh@gmail.com", courses);
		Student studentTwo= new Student("Theeraj", "Gutam", "theeraj@gmail.com", courses);
		students.add(studentOne);
		students.add(studentTwo);
		courses.setStudents(students);
		
		Set<Student> studentsSet = new HashSet<>();
		Student studentThree = new Student("Teja", "Gutam", "teja@gmail.com", coursesTwo);
		Student studentFour= new Student("Saanvitha", "Gutam", "saanvitha@gmail.com", coursesTwo);
		studentsSet.add(studentThree);
		studentsSet.add(studentFour);
		coursesTwo.setStudents(studentsSet);

		Set<Course> coursesSet = new HashSet<>();
		coursesSet.add(courses);
		coursesSet.add(coursesTwo);
		
		institute.setCourses(coursesSet);
		return institute;
		
	}

}
