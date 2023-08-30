package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

public record PersonDto(String username, String password, Role role) {


  public Person toPerson() {
    Person person = new Person();
    person.setUsername(username);
    person.setPassword(password);
    person.setRole(role);
    return person;
  }

  public PersonDto toDto() {
    return new PersonDto(username, password, role);
  }
}
