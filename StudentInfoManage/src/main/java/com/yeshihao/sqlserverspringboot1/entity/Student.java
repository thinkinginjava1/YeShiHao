package com.yeshihao.sqlserverspringboot1.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name="t_student")
public class Student {
    @Id
    @GeneratedValue
    private int id;

    public Student() {
    }

    private String name;
    private int age;
    private String password;
    private byte[] image;//图片

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(password, student.password) &&
                Arrays.equals(image, student.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, age, password);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Student(String name, int age, String password, byte[] image) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.image = image;
    }
}
