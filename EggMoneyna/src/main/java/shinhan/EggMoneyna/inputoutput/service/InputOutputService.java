package shinhan.EggMoneyna.inputoutput.service;


import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shinhan.EggMoneyna.account.entity.Account;
import shinhan.EggMoneyna.account.repository.AccountRepository;
import shinhan.EggMoneyna.comment.entity.Comment;
import shinhan.EggMoneyna.comment.repository.CommentRepository;
import shinhan.EggMoneyna.global.error.code.ErrorCode;
import shinhan.EggMoneyna.global.error.exception.BadRequestException;
import shinhan.EggMoneyna.inputoutput.dto.*;
import shinhan.EggMoneyna.inputoutput.entity.InputOutput;
import shinhan.EggMoneyna.inputoutput.repository.InputOutputRepository;
import shinhan.EggMoneyna.user.child.entity.Child;
import shinhan.EggMoneyna.user.child.repository.ChildRepository;
import shinhan.EggMoneyna.users.entity.Users;
import shinhan.EggMoneyna.users.repository.UsersRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InputOutputService {
    private final InputOutputRepository inputOutputRepository;
    private final AccountRepository accountRepository;
    private final UsersRepository usersRepository;
    private final CommentRepository commentRepository;
    private final ChildRepository childRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    final String EXTENSION = ".jpg";
    public HashMap<String, String> brandMap = new HashMap<String, String>() {
        {
            put("배스킨라빈스", "BaskinRobbins");
            put("버거킹(Burger King)", "BurgerKing");
            put("설빙", "Sulbing");
            put("세븐일레븐", "SevenEleven");
            put("씨유(CU)", "CU");
            put("아트박스", "ARTBOX");
            put("죠스떡볶이", "JawsTopokki");
            put("지에스25(GS25)", "GS25");
            put("투썸플레이스", "ATwosomePlace");
            put("파리바게뜨", "ParisBaguette");
            put("한솥", "Hansot");
            put("롯데리아", "Lotteria");
            put("Olive Young(올리브영)", "OliveYoung");
        }
    };

    public AddInputOutputResponseDto addInput(Long usersId, AddInputOutRequestDto addInputOutRequestDto) throws IOException {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        Comment comment = Comment.builder()
                .childComment("")
                .parentComment("")
                .compliment(false)
                .isChild(false)
                .isParent(false)
                .build();

        commentRepository.save(comment);

        JsonNode itemsNode = getApi();
        String bigCategory = "";
        String smallCategory = "";
        String brandImage = "";
        for (JsonNode item : itemsNode) {
            String brandName = item.get("brandNm").asText();
            if (brandName.equals(addInputOutRequestDto.getBrandName())) {
                log.info(brandName);
                log.info(brandMap.get(brandName));
                String imageURL = amazonS3Client.getUrl(bucket, "brand/" + brandMap.get(addInputOutRequestDto.getBrandName()) + EXTENSION).toString();
                log.info(imageURL);
                bigCategory = item.get("indutyLclasNm").asText();
                smallCategory = item.get("indutyMlsfcNm").asText();
                brandImage = imageURL;
                break;
            }
        }
        InputOutput inputOutput = addInputOutRequestDto.of(account, comment, bigCategory, smallCategory, brandImage);
        inputOutputRepository.save(inputOutput);
        account.inBalance(addInputOutRequestDto.getInput());

        return AddInputOutputResponseDto.builder()
                .bigCategory(bigCategory)
                .smallCategory(smallCategory)
                .input(addInputOutRequestDto.getInput())
                .output(addInputOutRequestDto.getOutput())
                .build();
    }

    public AddInputOutputResponseDto addOutput(Long usersId, AddInputOutRequestDto addInputOutRequestDto) throws IOException {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        Comment comment = Comment.builder()
                .childComment("")
                .parentComment("")
                .compliment(false)
                .isChild(false)
                .isParent(false)
                .build();

        commentRepository.save(comment);

        JsonNode itemsNode = getApi();
        String bigCategory = "";
        String smallCategory = "";
        String brandImage = "";
        for (JsonNode item : itemsNode) {
            String brandName = item.get("brandNm").asText();
            if (brandName.equals(addInputOutRequestDto.getBrandName())) {
                log.info(brandName);
                log.info(brandMap.get(brandName));
                String imageURL = amazonS3Client.getUrl(bucket, "brand/" + brandMap.get(addInputOutRequestDto.getBrandName()) + EXTENSION).toString();
                log.info(imageURL);
                bigCategory = item.get("indutyLclasNm").asText();
                smallCategory = item.get("indutyMlsfcNm").asText();
                brandImage = imageURL;
                break;
            }
        }
        InputOutput inputOutput = addInputOutRequestDto.of(account, comment, bigCategory, smallCategory, brandImage);
        inputOutputRepository.save(inputOutput);
        account.outBalance(addInputOutRequestDto.getInput());

        return AddInputOutputResponseDto.builder()
                .bigCategory(bigCategory)
                .smallCategory(smallCategory)
                .input(addInputOutRequestDto.getInput())
                .output(addInputOutRequestDto.getOutput())
                .build();
    }

    public InputOutputResponseDto getInput(Long usersId, String inputDate) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputs = inputOutputRepository.findByAccountAndOutputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputs)
                .build();
    }

    public InputOutputResponseDto getOutput(Long usersId, String outputDate) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(outputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> outputs = inputOutputRepository.findByAccountAndInputAndCreateTimeBetween(account, 0, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(outputs)
                .build();
    }

    public InputOutputResponseDto getInputOutput(Long usersId, String inputOutputDate) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate, formatter);

        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23, 59, 59, 999999);

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfDay, endOfDay);

        return InputOutputResponseDto.builder()
                .inputOutputs(inputOutputs)
                .build();
    }

    public MonthOutputResponseDto getTotalMonthOutput(Long usersId, String inputOutputDate) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate + "-01", formatter); // 월의 첫 날로 설정

        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));

        int totalOutputs = inputOutputs.stream()
                .mapToInt(InputOutput::getOutput)
                .sum();

        return MonthOutputResponseDto.builder()
                .totalMonthOutput(totalOutputs)
                .build();
    }

    public MonthOutputResponseDto getTotalMonthInput(Long usersId, String inputOutputDate) {
        Child child = childRepository.findById(usersId).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));
        Account account = accountRepository.findByChild(child).orElseThrow(() -> new BadRequestException(ErrorCode.NOT_EXISTS_USER_ID));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(inputOutputDate + "-01", formatter); // 월의 첫 날로 설정

        LocalDate startOfMonth = localDate.withDayOfMonth(1);
        LocalDate endOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

        List<InputOutput> inputOutputs = inputOutputRepository.findByAccountAndCreateTimeBetween(account, startOfMonth.atStartOfDay(), endOfMonth.atTime(23, 59, 59));

        int totalInputs = inputOutputs.stream()
                .mapToInt(InputOutput::getInput)
                .sum();

        return MonthOutputResponseDto.builder()
                .totalMonthOutput(totalInputs)
                .build();
    }

    public JsonNode getApi() throws IOException {
        URL url = new URL("https://apis.data.go.kr/1130000/FftcBrandRlsInfoService/getBrandRlsInfo?serviceKey=VeIyQYlemcin0TUHlsQrWib5AWUE%2FUvpjhihs9Gkk%2BKuwBMDmqjoK3HnD9Dl1qxfXs6GIXPX13Ftdyi2RqkzGw%3D%3D&pageNo=1&numOfRows=10000&resultType=json&yr=2022");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode itemsNode = null;
        try {
            // JSON 문자열을 JsonNode로 파싱
            JsonNode rootNode = objectMapper.readTree(sb.toString());

            // "items" 배열 추출
            itemsNode = rootNode.get("items");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemsNode;
    }
}
