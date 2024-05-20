package com.example.sbnzproject.models;

import com.example.sbnzproject.config.Utils;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reader extends User {

    private static final long serialVersionUID = 1L;

    @Min(13)
    @Max(120)
    private long age;

    private boolean male;

    public String getCategory() {
        return Utils.getCategory(this);
    }

    public Reader(long id, String email, String password, String firstName, String lastName, long lastPasswordResetDate, ArrayList<Authority> authorities, boolean enabled) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, authorities, enabled);
    }
}