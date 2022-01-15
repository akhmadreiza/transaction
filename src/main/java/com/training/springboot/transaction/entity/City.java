package com.training.springboot.transaction.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "TBL_CITY")
public class City {
    @Id
    private String code;

    @NotNull
    private String name;

    //it is by default in lazy mode in this particular version of JPA.
    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
