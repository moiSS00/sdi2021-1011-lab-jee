package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Teacher;
import com.uniovi.services.TeacherServices;

@RestController
public class TeacherController {
	
	@Autowired // Inyectar el servicio
	private TeacherServices teacherService;

	@RequestMapping(value = "/teacher/add", method = RequestMethod.POST)
	public String setTeacher(@ModelAttribute Teacher teacher) {
		teacherService.addTeacher(teacher);
		return "Profesor añadido";
	}
	
	@RequestMapping(value = "/teacher/edit/{dni}", method = RequestMethod.POST)
	public String setMark(@PathVariable String dni, @ModelAttribute Teacher teacher) {
		teacher.setDNI(dni);
		teacherService.addTeacher(teacher);
		return teacherService.getTeacher(dni).toString();
	}
	
	@RequestMapping("/teacher/details/{dni}")
	public String setMark(@PathVariable String dni) {
		return teacherService.getTeacher(dni).toString();
	}
	
	@RequestMapping("/teacher/delete/{dni}")
	public String deleteTeacher(@PathVariable String dni) {
		teacherService.deleteTeacher(dni);
		return "Profesor eliminado";
	}

}
