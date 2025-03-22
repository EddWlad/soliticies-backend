package com.tidsec.solicities_service.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "accrual")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accrual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccrual;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime date = LocalDateTime.now();

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double quantityUsed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stockTaking", foreignKey = @ForeignKey(name = "FK_ACCRUAL_STOCKTAKING"))
    private StockTaking stockTaking;

    @Column(nullable = false)
    private Integer status = 1;

}
