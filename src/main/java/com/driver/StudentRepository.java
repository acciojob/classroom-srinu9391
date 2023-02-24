package com.driver;


import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository
{
    Map<String,Student> hm_students;
    Map<String, Teacher> hm_teachers;
    Map<String, String> hm_student_teacher;

    public StudentRepository()
    {
        hm_students =  new HashMap<>();
        hm_teachers =  new HashMap<>();
        hm_student_teacher = new HashMap<>();
    }


    public void addStudent( Student student)
    {
        hm_students.put(student.getName(),student);
    }

    public void addTeacher( Teacher teacher)
    {
        hm_teachers.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair( String student, String teacher)
    {
        hm_student_teacher.put(student,teacher);
    }

    public Student getStudentByName( String name)
    {
        return hm_students.get(name);
    }

    public Teacher getTeacherByName( String name)
    {
        return hm_teachers.get(name);
    }

    public List<String> getStudentsByTeacherName( String teacher)
    {
        List<String> ret = new ArrayList<>();
        for(String student : hm_student_teacher.keySet())
        {
            if(hm_student_teacher.get(student).equalsIgnoreCase(teacher)) ret.add(student);
        }
        return ret;
    }

    public List<String> getAllStudents()
    {
        return new ArrayList<>(hm_students.keySet());
    }

    public void deleteTeacherByName( String teacher)
    {
        hm_teachers.remove(teacher);

        List<String> itr = new ArrayList<>(hm_student_teacher.keySet()); // to avoid concurrent exception
        for(String stud : itr)
        {
            if(hm_student_teacher.get(stud).equalsIgnoreCase(teacher))
            {
                hm_students.remove(stud);
                hm_student_teacher.remove(stud);
            }
        }
    }

    public void deleteAllTeachers()
    {
        for(String stud : hm_student_teacher.keySet())
        {
            hm_students.remove(stud);
        }
        hm_teachers = new HashMap<>();
        hm_student_teacher = new HashMap<>();
    }





}
