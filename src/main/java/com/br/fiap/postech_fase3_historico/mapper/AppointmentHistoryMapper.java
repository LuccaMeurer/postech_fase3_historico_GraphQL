package com.br.fiap.postech_fase3_historico.mapper;

import com.br.fiap.postech_fase3_historico.dto.AppointmentHistoryDTO;
import com.br.fiap.postech_fase3_historico.model.AppointmentHistory;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class AppointmentHistoryMapper {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final ZoneOffset OFFSET = ZoneOffset.of("-03:00");

    public static AppointmentHistoryDTO toDTO(AppointmentHistory e) {
        String created = e.getCreatedAt() != null
                ? e.getCreatedAt().atOffset(OFFSET).format(ISO)
                : null;

        String updated = e.getUpdatedAt() != null
                ? e.getUpdatedAt().atOffset(OFFSET).format(ISO)
                : null;

        return new AppointmentHistoryDTO(
                e.getId(),
                e.getAppointmentId(),
                e.getPatientId(),
                e.getDoctorId(),
                created,
                updated,
                e.getStatus(),
                e.getNotes()
        );
    }
}
