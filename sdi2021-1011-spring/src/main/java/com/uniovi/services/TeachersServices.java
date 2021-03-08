package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Teacher;
import com.uniovi.repositories.TeachersRepository;

@Service
public class TeachersServices {

	@Autowired
	private TeachersRepository teachersRepository;
	

	public void addTeacher(Teacher teacher) {
		teachersRepository.save(teacher); 
	}

	public void deleteTeacher(String dni) {
		teachersRepository.deleteById(dni);
	}

	public Teacher getTeacher(String dni) {
		return teachersRepository.findById(dni).get(); 
	}
	
	public List<Teacher> getTeachers() {
		List<Teacher> users = new ArrayList<Teacher>();
		teachersRepository.findAll().forEach(users::add);
		return users;
	}
	
	

}
