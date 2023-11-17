package com.billy.operations.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.*;

@Getter
@Setter
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
        @Schema(description = "user email for notifications")
        private String email;
        private String phoneNumber;
        private Integer birthYear;
        @Schema(description = "Unique tax identification number")
        private String RFC;
        private Profile profile;
        @Schema(description = "nationality from person")
        private JobNationality jobNationality;
        @Schema(description = "Regimes that applies to this person")
        private Set<Tax> regime = new HashSet<>();
        @Relationship(type = "HAS_BILL", direction = Relationship.Direction.OUTGOING)
        private Set<Bill> bills = new HashSet<>();



}
