package com.ssg.secondproject.controller.api;

import com.ssg.secondproject.dto.WarehouseDTO;
import com.ssg.secondproject.dto.request.WarehouseRequestDTO;
import com.ssg.secondproject.dto.request.WarehouseUpdateDTO;
import com.ssg.secondproject.dto.response.WarehouseResponseDTO;
import com.ssg.secondproject.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Log4j2
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseRestController {
    private final WarehouseService warehouseService;
    private final ModelMapper modelMapper;

    @GetMapping("/names")
    public String getWarehouseList() {
        log.info(" 🎉🎉🎉🎉 Rest Warehouse List 🎉🎉🎉🎉");
        return """
            {
              "status": "success",
              "data": {
                "warehouseNameList": [
                  { "id": 1, "name": "서울 창고" },
                  { "id": 2, "name": "의정부 창고" },
                  { "id": 3, "name": "남양주 창고" },
                  { "id": 4, "name": "천안 창고" },
                  { "id": 5, "name": "이천 창고" }
                ]
              }
            }
            """;
    }

    @PostMapping("")
    public ResponseEntity<?> register(@Valid @RequestBody WarehouseRequestDTO warehouseRequestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        log.info(warehouseRequestDTO);

        WarehouseDTO warehouseDTO = modelMapper.map(warehouseRequestDTO, WarehouseDTO.class);

        log.info(warehouseDTO);


        int result = warehouseService.create(warehouseDTO);

        log.info(result);


        String msg;
        int status;

        if (result == 1) {
            msg = "창고가 생성되었습니다.";
            status = 200;
        } else {
            msg = "창고가 생성되지 않았습니다.";
            status = 400;
        }

        WarehouseResponseDTO warehouseResponseDTO = WarehouseResponseDTO.builder()
                .msg(msg)
                .build();

        return ResponseEntity
                .status(status)
                .body(warehouseResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouse(@PathVariable int id) {
        WarehouseDTO warehouseDTO = warehouseService.get(id);

        WarehouseResponseDTO warehouseResponseDTO = null;

        if (warehouseDTO != null) {
            warehouseResponseDTO = WarehouseResponseDTO.builder()
                    .warehouseDTO(warehouseDTO)
                    .build();
        }

        return ResponseEntity.ok().body(warehouseResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWarehouse(@PathVariable int id, @RequestBody WarehouseUpdateDTO warehouseUpdateDTO) {
        WarehouseDTO warehouseDTO = WarehouseDTO.builder()
                .id(id)
                .name(warehouseUpdateDTO.getName())
                .size(warehouseUpdateDTO.getSize())
                .capacity(warehouseUpdateDTO.getCapacity())
                .adminId(warehouseUpdateDTO.getAdminId())
                .latitude(warehouseUpdateDTO.getLatitude())
                .longitude(warehouseUpdateDTO.getLongitude())
                .address(warehouseUpdateDTO.getAddress())
                .build();

        warehouseService.update(warehouseDTO);

        WarehouseResponseDTO warehouseResponseDTO = WarehouseResponseDTO.builder().msg(
                "창고가 성공적으로 수정되었습니다."
        ).build();

        return ResponseEntity.ok()
                .body(warehouseResponseDTO);
    }
}
