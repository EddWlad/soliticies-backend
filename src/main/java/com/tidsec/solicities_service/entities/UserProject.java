package com.tidsec.solicities_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserProjectPK.class)
public class UserProject {
    @Id
    private User user;

    @Id
    private Project project;
}
