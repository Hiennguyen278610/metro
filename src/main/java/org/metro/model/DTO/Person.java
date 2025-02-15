package org.metro.model.DTO;

public abstract class Person {
    private String firstName; // Họ
    private String lastName; // Tên
    private Gender gender; // Giới tính
    private String phoneNumber; // Số điện thoại

    public Person(Person person) {
        this.firstName = person.firstName;
        this.lastName = person.lastName;
        this.gender = person.gender;
        this.phoneNumber = person.phoneNumber;
    }
}
