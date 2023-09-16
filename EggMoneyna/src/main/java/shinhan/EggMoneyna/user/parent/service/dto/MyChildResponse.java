package shinhan.EggMoneyna.user.parent.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyChildResponse {

    private String childName;
    private int balance;
    private Long accountNumber;
    private List<InputOutput> inputOutput;
    private String attempt;
    private int commentCnt;
}
