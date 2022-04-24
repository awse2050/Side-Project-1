package com.example.check.api.domains.attach.repository;

import com.example.check.api.domains.attach.entity.Attach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<Attach, Long> {
}
