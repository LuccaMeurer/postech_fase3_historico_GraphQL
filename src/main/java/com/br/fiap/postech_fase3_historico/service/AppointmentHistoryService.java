package com.br.fiap.postech_fase3_historico.service;


import com.br.fiap.postech_fase3_historico.dto.AppointmentHistoryDTO;
import com.br.fiap.postech_fase3_historico.mapper.AppointmentHistoryMapper;
import com.br.fiap.postech_fase3_historico.model.AuthenticatedUser;
import com.br.fiap.postech_fase3_historico.repository.AppointmentHistoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentHistoryService {

    private final AppointmentHistoryRepository repository;

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public AppointmentHistoryDTO findById(Long id) {
        return repository.findById(id)
                .map(AppointmentHistoryMapper::toDTO)
                .orElse(null);
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public List<AppointmentHistoryDTO> listAll(int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findAll(pageable)
                .map(AppointmentHistoryMapper::toDTO)
                .getContent();
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public List<AppointmentHistoryDTO> findByAppointment(Long appointmentId, int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findByAppointmentId(appointmentId, pageable)
                .map(AppointmentHistoryMapper::toDTO)
                .getContent();
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public List<AppointmentHistoryDTO> findByPatient(Long patientId, int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findByPatientId(patientId, pageable)
                .map(AppointmentHistoryMapper::toDTO)
                .getContent();
    }

    @PreAuthorize("hasAnyRole('MEDICO', 'ENFERMEIRO')")
    public List<AppointmentHistoryDTO> findByDoctor(Long doctorId, int page, int size) {
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findByDoctorId(doctorId, pageable)
                .map(AppointmentHistoryMapper::toDTO)
                .getContent();
    }

    @PreAuthorize("hasRole('PACIENTE')")
    public List<AppointmentHistoryDTO> findByCurrentPatient(int page, int size) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var user = (AuthenticatedUser) auth.getPrincipal();
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return repository.findByPatientId(user.getId(), pageable)
                .map(AppointmentHistoryMapper::toDTO)
                .getContent();
    }
}
