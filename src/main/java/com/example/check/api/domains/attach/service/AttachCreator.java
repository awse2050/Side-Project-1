package com.example.check.api.domains.attach.service;

import com.example.check.api.domains.attach.entity.Attach;
import com.example.check.api.domains.attach.repository.AttachRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class AttachCreator {

    private final AttachRepository attachRepository;

    public Long create(Attach attach) {
        Attach save = attachRepository.save(attach);

        log.info("save  : {}", save);

        return save.getId();
    }


}
