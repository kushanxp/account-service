package com.micro.accountservice.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "customer_id")
    private Long customerId;


    private String name;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;


}
