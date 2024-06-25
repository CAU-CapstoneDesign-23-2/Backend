package com.personal.doctor.CapstoneDesign.chat.service;

import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatRequestDto;
import com.personal.doctor.CapstoneDesign.chat.controller.dto.ChatSaveRequestDto;
import com.personal.doctor.CapstoneDesign.chat.domain.Chat;
import com.personal.doctor.CapstoneDesign.chat.domain.ChatRepository;
import com.personal.doctor.CapstoneDesign.user.domain.Users;
import com.personal.doctor.CapstoneDesign.user.domain.UsersRepository;
import com.personal.doctor.CapstoneDesign.util.exceptions.UserNotExistException;
import com.personal.doctor.CapstoneDesign.userDetail.domain.Details;
import com.personal.doctor.CapstoneDesign.userDetail.domain.DetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

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

    // 채팅 저장
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

    // 사용자의 모든 채팅 반환
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

    // GPT 요청 처리
    public String handleGptRequest(Long userId, String userText) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new UserNotExistException("사용자가 존재하지 않습니다."));

        // 사용자의 세부 정보 가져오기
        Details userDetails = detailsRepository.findUserDetails(userId);
        if (userDetails == null) {
            throw new UserNotExistException("사용자의 세부 정보가 존재하지 않습니다.");
        }

        // GPT API 호출
        return callGptApi(userText, users, userDetails);
    }

    private String callGptApi(String userText, Users users, Details userDetails) {
        String apiUrl = "https://api.openai.com/v1/engines/gpt-4o/completions";  // GPT-4o API 엔드포인트

        // 사용자 세부 정보 문자열 생성
        String userDetailString = createUserDetailString(users, userDetails);

        String totalRequest = userDetailString + " " + userText;

        // HTTP 요청 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "sk-proj-WDsPyXuk5kIxE7sgPTu9T3BlbkFJsjImqXHqrZQSnCaOHXBn\n");  // GPT API 키 설정
        String requestBody = String.format("{\"prompt\": \"%s\", \"max_tokens\": 1024}", totalRequest);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, Map.class);

        // GPT 응답 반환
        Map<String, Object> responseBody = response.getBody();
        List<Map<String, String>> choices = (List<Map<String, String>>) responseBody.get("choices");
        String gptResponse = choices.get(0).get("text").trim();

        return gptResponse;
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
