package com.training.springboot.transaction.examples;

import java.util.ArrayList;
import java.util.List;

public class NativeDependencyInjectionExample {
    
    //type "psvm" in your intellij, then will generate main method
    public static void main(String[] args) {
        DemoConfiguration demoConfiguration = new DemoConfiguration();
        DemoService demoService = new DemoService(demoConfiguration.person());
        demoService.getPersonList().forEach(personDto1 -> {
            System.out.println(personDto1.getName());
        });
    }

    private static class PersonDto {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    
    private static class DemoConfiguration {
        public PersonDto person() {
            PersonDto personDto = new PersonDto();
            personDto.setName("SUPER_ADMIN");
            return personDto;
        }
    }

    private static class DemoService {
        private PersonDto personDto;

        public DemoService(PersonDto personDto) {
            this.personDto = personDto;
        }

        public List<PersonDto> getPersonList() {
            List<PersonDto> personDtoList = new ArrayList<>();
            personDtoList.add(personDto);
            return personDtoList;
        }
    }
}
