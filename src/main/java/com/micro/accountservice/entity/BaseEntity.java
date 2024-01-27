package com.micro.accountservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class BaseEntity {

    @Column(updatable = false)
    private String createdAt;

    @Column(updatable = false)
    private String createdBy;

    @Column(insertable = false)
    private String updatedAt;

    @Column(insertable = false)
    private String updatedBy;
}
