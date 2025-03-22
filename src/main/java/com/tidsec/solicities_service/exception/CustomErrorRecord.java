package com.tidsec.solicities_service.exception;

import java.time.LocalDateTime;

public record CustomErrorRecord(
        LocalDateTime dateTime,
        String message,
        String details
){ }

