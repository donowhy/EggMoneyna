package shinhan.EggMoneyna.inputoutput.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shinhan.EggMoneyna.inputoutput.dto.AddInputOutRequestDto;
import shinhan.EggMoneyna.inputoutput.dto.AddInputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.dto.InputOutputResponseDto;
import shinhan.EggMoneyna.inputoutput.service.InputOutputService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/inputoutput")
@RequiredArgsConstructor
public class InputOutputController {
    private final InputOutputService inputOutputService;

    @Operation(summary = "입금 추가", description = "사용자 계좌에 입금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/input")
    public ResponseEntity<AddInputOutputResponseDto> addInput(@UserInfo UsersInfo usersInfo, @RequestBody AddInputOutRequestDto addInputOutRequestDto) {
        return ResponseEntity.ok(inputOutputService.addInput(usersInfo.getId(), addInputOutRequestDto));
    }

    @Operation(summary = "출금 추가", description = "사용자 계좌에 출금이 추가됩니다.", tags = { "InputOutput Controller" })
    @PostMapping("/output")
    public ResponseEntity<AddInputOutputResponseDto> addOutput(@UserInfo UsersInfo usersInfo, @RequestBody AddInputOutRequestDto addInputOutRequestDto) {
        return ResponseEntity.ok(inputOutputService.addOutput(usersInfo.getId(), addInputOutRequestDto));
    }

    @Operation(summary = "당일 입금 조회", description = "사용자 계좌에 해당하는 날짜의 입금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/input/{inputDate}")
    public ResponseEntity<InputOutputResponseDto> getInput(@UserInfo UsersInfo usersInfo, @PathVariable String inputDate) {
        return ResponseEntity.ok(inputOutputService.getInput(usersInfo.getId(), inputDate));
    }

    @Operation(summary = "당일 출금 조회", description = "사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/output/{outputDate}")
    public ResponseEntity<InputOutputResponseDto> getOutput(@UserInfo UsersInfo usersInfo, @PathVariable String outputDate) {
        return ResponseEntity.ok(inputOutputService.getOutput(usersInfo.getId(), outputDate));
    }

    @Operation(summary = "당일 입출금 조회", description = "사용자 계좌에 해당하는 날짜의 출금내역을 조회합니다.", tags = { "InputOutput Controller" })
    @PostMapping("/all/{inputOuputDate}")
    public ResponseEntity<InputOutputResponseDto> getInputOutput(@UserInfo UsersInfo usersInfo, @PathVariable String inputOuputDate) {
        return ResponseEntity.ok(inputOutputService.getInputOutput(usersInfo.getId(), inputOuputDate));
    }

}
