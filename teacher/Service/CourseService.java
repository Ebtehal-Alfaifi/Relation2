package com.example.teacher.Service;

import com.example.teacher.Api.ApiException;
import com.example.teacher.Model.Course;
import com.example.teacher.Model.Teacher;
import com.example.teacher.Repository.CourseRepository;
import com.example.teacher.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


    public List<Course>getAll(){
        return courseRepository.findAll();
    }


    public void addNew(Integer teacherId,Course course){
        Teacher teacher=teacherRepository.findTeacherByTeacherId(teacherId);
        if (teacher==null){
            throw new ApiException("teacher not found");

        }

        course.setTeacher(teacher);
        courseRepository.save(course);

    }

    public void updateCourse(Integer id,Course course){
        Course course1=courseRepository.findCourseById(id);
        if (course1==null){
            throw new ApiException(" course not found");
        }

        course1.setName(course.getName());

    }


    // هذي ما بتنحذف الا اذا حذفت التيجر قبل
    public void delete(Integer id){
        Course course=courseRepository.findCourseById(id);
        if (course==null){
            throw new ApiException("course notfound");
        }

        courseRepository.delete(course);

    }



    //Create endpoint that take course id and return the teacher name for that class



    public String getTeacherNameByCourseId(Integer courseId) {
        Course course = courseRepository.findCourseById(courseId) ;
       if (course==null){
           throw new ApiException("course not found ");
       }
        Teacher teacher = course.getTeacher();
       return teacher.getName();
    }



}
