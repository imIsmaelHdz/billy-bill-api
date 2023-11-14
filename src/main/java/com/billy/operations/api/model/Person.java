package com.billy.operations.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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
        private String email;
        private Integer birthYear;
        @Schema(description = "Unique tax identification number")
        private String RFC;
        @Schema(description = "nationality from person")
        private Nationality nationality;
}
