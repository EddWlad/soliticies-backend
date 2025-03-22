package com.tidsec.solicities_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name= "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idCompany;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 50)
    private String ruc;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String name;

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String address;

    @OneToOne(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private MediaFileLogo logo;

    @Column(nullable = false)
    private Integer status = 1;
}
