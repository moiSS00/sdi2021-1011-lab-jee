package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;

@Service
public class TeacherServices {

	private List<Teacher> teachers = new ArrayList<Teacher>();
	
	@PostConstruct
	public void init() {
		teachers.add(new Teacher("1", "nombre1", "apellidos1", "A")); 
		teachers.add(new Teacher("2", "nombre2", "apellidos2", "B")); 
	}

	public void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	public void deleteTeacher(String dni) {
		teachers.removeIf(teacher -> teacher.getDNI().equals(dni));
	}

	public Teacher getTeacher(String dni) {
		return teachers.stream().filter(teacher -> teacher.getDNI().equals(dni)).findFirst().get();
	}
	
	

}
