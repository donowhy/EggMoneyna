package shinhan.EggMoneyna.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
@NoArgsConstructor
@Builder
public class ReturnBalance {

    private int balance;
}
