package com.billy.operations.api.model;

import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {
        @Id
        @GeneratedValue
        private UUID customId;
        private String name;
        private String lastName;
        private Integer birthYear;
        @Description("Unique tax identification number")
        private String RFC;
        @Description("nationality from person")
        private Nationality nationality;
}
