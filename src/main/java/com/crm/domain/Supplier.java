package com.crm.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = true, unique = true)
    private String email;

    @Column(length = 50, nullable = false)
    private String ownerFirstName;

    @Column(length = 50, nullable = false)
    private String ownerLastName;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    private String city;

    @Column(length = 14, nullable = false)
    private String phone;

    @Column(length = 14)
    private String ownerWhatsapp;

    @Column(length = 50)
    private String linkedPage;

    @Column(length = 50)
    private String webPage;

    // @Column
    // private Double totalAmount; //her satışta üzerine eklencek

    //@OneToMany(mappedBy = "supplier")
    //private List<OrderedProducts> orderedProducts = new ArrayList<>();


    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "orders_id")
    //private Orders orders;

    @ManyToMany(mappedBy = "suppliers")
    private List<Orders> orders;

    @ManyToMany(mappedBy = "suppliers")
    private List<OrderedProducts> orderedProducts;

}
