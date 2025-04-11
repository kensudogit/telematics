package com.example.telematics.repository;

import com.example.telematics.entity.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLogRepository extends JpaRepository<ServiceLog, Long> {
}