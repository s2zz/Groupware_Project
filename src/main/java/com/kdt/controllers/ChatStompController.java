package com.kdt.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kdt.dto.ChatMessageDTO;
import com.kdt.services.ChatMessageService;

@Controller
public class ChatStompController {
	@Autowired
	private ChatMessageService chatMessageService;

	@Autowired
	private Gson gson;

	@MessageMapping("chat/message")
	@SendTo("/topic/chat")
	public ChatMessageDTO message(ChatMessageDTO message) {
		return message;
	}

	@MessageMapping("/oneToOne/sendMessage/{oneSeq}")
	@SendTo("/topic/oneToOne/{oneSeq}")
	public int oneToOne_insert(@Payload String message, @DestinationVariable String oneSeq) throws Exception {
	    System.out.println(message);
		ChatMessageDTO dto = gson.fromJson(message, new TypeToken<ChatMessageDTO>() {}.getType());
	    System.out.println("확인"+oneSeq);
	    return chatMessageService.insert(dto);
	}
	@ResponseBody
	@RequestMapping("/getPreviousMessages/{oneSeq}")//login된 userID를 where절로 줘야함 이름임
	public List<ChatMessageDTO> getPreviousMessages(@PathVariable String oneSeq) {
		System.out.println("이전" +oneSeq);
	    List<ChatMessageDTO> previousMessages = chatMessageService.getPreviousMessages(oneSeq);
	    return previousMessages;
	}




	@MessageMapping("/group/sendMessage")
	@SendTo("/topic/group/{groupId}")
	public int group_insert(ChatMessageDTO group_dto) throws Exception {
		return chatMessageService.insert(group_dto);
	}

	@RequestMapping(value = "/chat/selectByType", method = RequestMethod.GET)
	public List<ChatMessageDTO> selectChat(@RequestParam("type") String type, @RequestParam("MessageSeq") int MessageSeq) {
		List<ChatMessageDTO> dto = new ArrayList<>();

		if ("oneToOne".equals(type)) {
			// 일대일 채팅을 가져오는 로직
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("type", "oneToOne");
			parameterMap.put("MessageSeq", MessageSeq);
			dto = chatMessageService.selectByType(parameterMap);
		} else if ("group".equals(type)) {
			// 그룹 채팅을 가져오는 로직
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("type", "group");
			parameterMap.put("MessageSeq", MessageSeq);
			dto = chatMessageService.selectByType(parameterMap);
		}

		// 나머지 로직
		return dto;
	}



}