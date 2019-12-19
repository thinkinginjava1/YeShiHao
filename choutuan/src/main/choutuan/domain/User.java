package domain;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private static final long serialVersionUID = -3317921830291670995L;
    //不加上这个值，将无法实现反序列化
    private Long id;//主键
    private String name;
    private String password;
    private String gender;
    private String  birthday;
    private String address;
    private String phoneNumber;
    private Set<HotelHome> hotels =new HashSet<>();//


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    //这里是多的一方
    public User() {
    }
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User( String name, String password, String gender, String birthday, String address, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(address, user.address) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(hotels, user.hotels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, gender, birthday, address, phoneNumber);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<HotelHome> getHotels() {
        return hotels;
    }

    public void setHotels(Set<HotelHome> hotel) {
        this.hotels = hotel;
    }
}
