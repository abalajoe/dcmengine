package com.dcm.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "sellerid", referencedColumnName = "ID")
    private User sellerid;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "buyerid", referencedColumnName = "ID")
    private User buyerid;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "supplier", referencedColumnName = "ID")
    private Supplier supplier;
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "orders", referencedColumnName = "ID")
    private Order orders;
    private int quantity;
    private int status;
    private LocalDateTime datecreated;
}