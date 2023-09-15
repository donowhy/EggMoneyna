package shinhan.EggMoneyna.inputoutput.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.CategoryGraphDto;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InputOutputGraphService {
    private final ChildRepository childRepository;
    private final AccountRepository accountRepository;
    private final InputOutputRepository inputOutputRepository;

    final String[] smallCategory1 = {"편의점"};
    final String[] smallCategory2 = {"분식", "패스트푸드", "한식", "일식", "중식", "서양식"};
    final String[] smallCategory3 = {"오락"};
    final String[] smallCategory4 = {"기타도소매", "종합소매점"};
    final String[] smallCategory5 = {"커피", "아이스크림/빙수"};

    public CategoryGraphDto getCategoryGraph (Long usersId) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        LocalDate date = LocalDate.now();
        String year = String.valueOf(date.getYear());
        String month = "";
        if (date.getMonthValue() < 10) {
            month = "0" + String.valueOf(date.getMonthValue());
        } else {
            month = String.valueOf(date.getMonthValue());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(year + "-" + month + "-01", formatter); // 월의 첫 날로 설정

        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));
        int size = 0;

        int convenienceStore = 0;
        int restaurants = 0;
        int game = 0;
        int shopping = 0;
        int cafe = 0;
        int etc = 0;
        for (InputOutput inputOutput : inputOutputs) {
            if (inputOutput.getInput() > 0) {
                continue;
            }
            size++;
            String category = inputOutput.getSmallCategory();
            if (Arrays.stream(smallCategory1).anyMatch(category::equals)) {
                convenienceStore++;
            } else if (Arrays.stream(smallCategory2).anyMatch(category::equals)) {
                restaurants++;
            } else if (Arrays.stream(smallCategory3).anyMatch(category::equals)) {
                game++;
            } else if (Arrays.stream(smallCategory4).anyMatch(category::equals)) {
                shopping++;
            } else if (Arrays.stream(smallCategory5).anyMatch(category::equals)) {
                cafe++;
            } else {
                etc++;
            }
        }

        double convenienceStorePercentage = Math.round(((double) convenienceStore / size * 100.0) * 10.0) / 10.0;
        double restaurantsPercentage = Math.round(((double) restaurants / size * 100.0) * 10.0) / 10.0;
        double gamePercentage = Math.round(((double) game / size * 100.0) * 10.0) / 10.0;
        double shoppingPercentage = Math.round(((double) shopping / size * 100.0) * 10.0) / 10.0;
        double cafePercentage = Math.round(((double) cafe / size * 100.0) * 10.0) / 10.0;
        double etcPercentage = Math.round(((double) etc / size * 100.0) * 10.0) / 10.0;

        return CategoryGraphDto.builder()
                .convenienceStore(convenienceStorePercentage)
                .restaurants(restaurantsPercentage)
                .game(gamePercentage)
                .shopping(shoppingPercentage)
                .cafe(cafePercentage)
                .etc(etcPercentage)
                .build();

    }
}
