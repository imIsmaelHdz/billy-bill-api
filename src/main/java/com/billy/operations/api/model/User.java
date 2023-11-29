package com.billy.operations.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
public class User {
        @Id
        @GeneratedValue
        private UUID customId;
        private String name;
        private String lastName;
        private char[] password;
        private String email;
        private String phoneNumber;
        private Integer birthYear;
        private Profile profile;
        private String RFC;
        private JobNationality jobNationality;

        @Relationship(type = "HAS_TAX", direction = Relationship.Direction.OUTGOING)
        private Set<Tax> regimes = new HashSet<>();

        @Relationship(type = "HAS_BILL", direction = Relationship.Direction.OUTGOING)
        private Set<Bill> bills = new HashSet<>();
}
