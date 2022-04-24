package com.example.check.api.domains.attach.entity;

import com.example.check.api.util.entity.DateEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Attach extends DateEntity {

    // 번호, 할일 게시물번호, 업로드시 이름, 저장할 때 이름
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACH_ID")
    private Long id;

    @Column(name = "UPLOAD_FILE_NAME")
    private String uploadFileName;

    @Column(name = "TO_SAVED_FILE_NAME")
    private String toSavedFileName;

}
