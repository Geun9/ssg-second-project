package com.ssg.secondproject.service;

import com.ssg.secondproject.dto.response.OutboundResponseDTO;

import java.time.LocalDate;

public interface OutboundService {
    PageListResponseDTO<OutboundResponseDTO> getList(PageRequestDTO pageRequestDTO);
    //출고 요청서 상세 페이지 데이터 가지고 오기
    PageResponseDTO<OutboundResponseDTO> getById(int id);
    //출고 요청서 상세 페이지 내에 출고 승인 내역 데이터 가지고 오기
    PageResponseDTO<OutboundResponseDTO> getByIdLog(int id);

    PageListResponseDTO<OutboundResponseDTO> getByUserInput(PageRequestDTO pageRequestDTO, String warehouseName, String approvalStatus, LocalDate startDate, LocalDate endDate);

    void modifyStatus(int id, String approvalStatus, Long rejectionReason);
}