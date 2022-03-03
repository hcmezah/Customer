package com.p1.users.project1NTTData.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="customer")
public class Customer {

    @Id
    private String _id;
    private String name;
    private String lastName;
    private String nroDocument;
    private String type;
    private String subType;
    private Boolean status;

}