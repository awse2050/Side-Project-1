package com.example.check.api.util.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class DateEntity {

    @CreatedDate
    @Column(name = "regdate", nullable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate", nullable = false)
    private LocalDateTime modDate;

}
