package com.ssg.secondproject.domain;

import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    private Integer id;

    private Integer adminId;

    private String name;

    private int size;

    private int capacity;

    private double latitude;

    private double longitude;

    private String address;


    private LocalDateTime createdAt;

//    최종 수정자 id;
    private Integer updatedId;

    private LocalDateTime updatedAt;
}
