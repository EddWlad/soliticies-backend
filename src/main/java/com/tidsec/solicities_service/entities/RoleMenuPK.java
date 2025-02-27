package com.tidsec.solicities_service.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class RoleMenuPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "id_role", foreignKey = @ForeignKey(name = "FK_ROLE_MENU_R"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "id_menu", foreignKey = @ForeignKey(name = "FK_ROLE_MENU_M"))
    private Menu menu;
}
