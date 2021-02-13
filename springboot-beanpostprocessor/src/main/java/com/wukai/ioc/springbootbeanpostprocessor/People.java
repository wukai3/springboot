package com.wukai.ioc.springbootbeanpostprocessor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class People {
    private String name;
    private String phone;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

