package com.br.fiap.postech_fase3_historico.dto;

public record AppointmentHistoryDTO(
        Long id,
        Long appointmentId,
        Long patientId,
        Long doctorId,
        String createdAt,
        String updatedAt,
        String status,
        String notes
){}
