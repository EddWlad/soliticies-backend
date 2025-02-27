package com.tidsec.solicities_service.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class UserProjectPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_USER_PROJECT_U"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_project", foreignKey = @ForeignKey(name = "FK_USER_PROJECT_P"))
    private Project project;
}
