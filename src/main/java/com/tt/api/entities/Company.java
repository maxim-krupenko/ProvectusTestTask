package com.tt.api.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "companies")
@Getter
@Setter
public class Company extends AbstractEntity {
    private String name;
}
