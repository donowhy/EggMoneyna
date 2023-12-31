package shinhan.EggMoneyna.inputoutput.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.CategoryGraphDto;
import shinhan.EggMoneyna.inputoutput.dto.WeekGraphDto;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InputOutputGraphService {
    private final ChildRepository childRepository;
    private final AccountRepository accountRepository;
    private final InputOutputRepository inputOutputRepository;

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
            if (category.equals("편의점")) {
                convenienceStore++;
            } else if (category.equals("외식")) {
                restaurants++;
            } else if (category.equals("오락")) {
                game++;
            } else if (category.equals("쇼핑")) {
                shopping++;
            } else if (category.equals("카페")) {
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

    public List<WeekGraphDto> getWeekGraph(Long usersId) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);
        List<WeekGraphDto> WeekList = new ArrayList<>();

        for (int i = 1; i < 8; i++) {
            LocalDate startDate = oneWeekAgo.plusDays(i);
            log.info(startDate.toString());
            LocalDateTime endDate = oneWeekAgo.plusDays(i).atTime(23, 59, 59);
            log.info(endDate.toString());
            List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startDate.atStartOfDay(), endDate);

            int totalOutputs = inputOutputs.stream()
                    .mapToInt(InputOutput::getOutput)
                    .sum();

            int totalInputs = inputOutputs.stream()
                    .mapToInt(InputOutput::getInput)
                    .sum();

            WeekGraphDto weekGraphDto = WeekGraphDto.builder()
                    .date(startDate)
                    .totalOutput(totalOutputs)
                    .totalInput(totalInputs)
                    .build();

            WeekList.add(weekGraphDto);
        }
        return WeekList;
    }
}
