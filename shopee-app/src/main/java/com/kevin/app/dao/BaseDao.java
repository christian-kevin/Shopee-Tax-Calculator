package com.kevin.app.dao;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class BaseDao implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name="id", nullable = false, unique = true)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    private Date createdAt;
}
