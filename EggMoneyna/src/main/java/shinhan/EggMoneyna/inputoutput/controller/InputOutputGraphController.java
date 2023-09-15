package shinhan.EggMoneyna.inputoutput.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shinhan.EggMoneyna.inputoutput.dto.CategoryGraphDto;
import shinhan.EggMoneyna.inputoutput.service.InputOutputGraphService;
import shinhan.EggMoneyna.jwt.UserInfo;
import shinhan.EggMoneyna.jwt.UsersInfo;

@RestController
@RequestMapping("/v1/graph")
@RequiredArgsConstructor
public class InputOutputGraphController {

    private final InputOutputGraphService inputOutputGraphService;

    @Operation(summary = "해당 월에 대한 카테고리 그래프", description = "해당 월의 카테고리 별 지출을 종합하여 백분율 카테고리 퍼센트를 제공 ", tags = { "InputOutputGraph Controller" })
    @PostMapping("/category")
    public ResponseEntity<CategoryGraphDto> getCategoryGraph(@UserInfo UsersInfo usersInfo) {
        return ResponseEntity.ok(inputOutputGraphService.getCategoryGraph(usersInfo.getId()));
    }
}
