package com.personal.doctor.CapstoneDesign.chat.service;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatRequestDto;
import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import com.personal.doctor.CapstoneDesign.userDetail.domain.DetailsRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final UsersRepository usersRepository;
    private final ChatRepository chatRepository;
    private final DetailsRepository detailsRepository;
    private final RestTemplate restTemplate;

    public ChatService(UsersRepository usersRepository, ChatRepository chatRepository, DetailsRepository detailsRepository, RestTemplate restTemplate) {
        this.usersRepository = usersRepository;
        this.chatRepository = chatRepository;
        this.detailsRepository = detailsRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public Long save(Long userId, ChatSaveRequestDto chatSaveRequestDto) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        Chat chat = chatSaveRequestDto.toEntity();
        chat.setUsers(users);
        users.addChats(chat);
        chatRepository.save(chat);

        return chat.getId();
    }

    @Transactional
    public List<ChatRequestDto> userChats(Long userId) {
        return chatRepository.findChatByUsersId(userId).stream()
                .map(ChatRequestDto::new)
                .toList();
    }

    @Transactional
    public void deleteAll() {
        chatRepository.deleteAll();
    }

    public String handleGptRequest(Long userId, String userText) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        Details userDetails = detailsRepository.findUserDetails(userId);
        if (userDetails == null) {
            throw new UserNotExistException("사용자의 세부 정보가 존재하지 않습니다.");
        }

        return callGptApi(userText, users, userDetails);
    }

    private String callGptApi(String userText, Users users, Details userDetails) {
        String apiUrl = "https://api.openai.com/v1/chat/completions"; // 올바른 엔드포인트 사용

        String userDetailString = createUserDetailString(users, userDetails);
        String totalRequest = userDetailString + " " + userText;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer {api key}");

        String requestBody = String.format("{\"model\": \"gpt-4o\", \"messages\": [{\"role\": \"system\", \"content\": \"%s\"}, {\"role\": \"user\", \"content\": \"%s\"}]}", userDetailString, userText);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        logger.info("Sending request to GPT API: {}", totalRequest);
        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            String gptResponse = (String) message.get("content");
            logger.info("Received response from GPT API: {}", gptResponse);
            return gptResponse.trim();
        } else {
            logger.error("Error from GPT API: {}", response.getStatusCode());
            return "Error: " + response.getStatusCode();
        }
    }

    private String createUserDetailString(Users users, Details userDetails) {
        String diseaseString = createStringFromDetails(userDetails.getDisease1(), userDetails.getDisease2(), userDetails.getDisease3(), "을 앓았던 이력이 있어, ");
        String hobbyString = createStringFromDetails(userDetails.getHobby1(), userDetails.getHobby2(), userDetails.getHobby3(), "를 취미로 하고, ");
        String medicineString = userDetails.getMedicine().isEmpty() ? "" : userDetails.getMedicine() + "을 복용 중이고, ";
        String surgeryString = userDetails.getSurgery().isEmpty() ? "" : userDetails.getSurgery() + " 경력이 있으며, ";
        String jobString = userDetails.getJob().isEmpty() ? "" : userDetails.getJob() + "을 직업으로 하고 있어.";

        return String.format("내가 %s이고 %s이고 %s을 직업으로 하고있어.%s%s%s%s%s",
                userDetails.getAge(), userDetails.getGender(), jobString, diseaseString, hobbyString, medicineString, surgeryString, jobString);
    }

    private String createStringFromDetails(String detail1, String detail2, String detail3, String suffix) {
        if (detail1.isEmpty() && detail2.isEmpty() && detail3.isEmpty()) {
            return "";
        } else if (detail2.isEmpty() && detail3.isEmpty()) {
            return detail1 + suffix;
        } else if (detail3.isEmpty()) {
            return detail1 + ", " + detail2 + suffix;
        } else {
            return detail1 + ", " + detail2 + ", " + detail3 + suffix;
        }
    }
}