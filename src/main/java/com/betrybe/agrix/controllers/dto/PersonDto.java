package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/** DTO que encapsula informações de uma pessoa. */
public record PersonDto(String username, String password, Role role) {

  /** para a pessoa. */
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
