package com.br.fiap.postech_fase3_historico.repository;

import com.br.fiap.postech_fase3_historico.model.AppointmentHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentHistoryRepository extends JpaRepository<AppointmentHistory, Long> {

    Page<AppointmentHistory> findByAppointmentId(Long appointmentId, Pageable pageable);

    Page<AppointmentHistory> findByPatientId(Long patientId, Pageable pageable);

    Page<AppointmentHistory> findByDoctorId(Long doctorId, Pageable pageable);
}


