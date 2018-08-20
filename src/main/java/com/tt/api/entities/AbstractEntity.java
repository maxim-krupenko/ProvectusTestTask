package com.tt.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@JsonIgnoreProperties({"CGLIB$BOUND", "CGLIB$CALLBACK_0", "target"})
public abstract class AbstractEntity {
    @Id
    private String id;
}
