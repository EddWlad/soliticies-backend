package com.tidsec.solicities_service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name= "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idProject;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(nullable = false)
    @Size(min = 3, max = 50)
    private String nameProject;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateStart;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateEnd;

    @Column(nullable = false)
    @Size(min = 3, max = 200)
    private String location;

    @Column(nullable = true)
    @Size(min = 3, max = 200)
    private String observation;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private StockTaking stockTaking;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_company", foreignKey = @ForeignKey(name = "FK_PROJECT_COMPANY"))
    private Company company;

    @Column(nullable = false)
    private Integer status = 1;
}
