package com.ssg.secondproject.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutboundRequestDTO {
    private int id;
    private int outboundId;
    private String status;
    private LocalDate createdAt = LocalDate.now();
    private Long rejectReason;
}
