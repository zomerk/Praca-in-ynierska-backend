package com.example.pracainzynierska.controller;

import com.example.pracainzynierska.entity.Message;
import com.example.pracainzynierska.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/userToTrainer")
    public Message sendMessageFromUserToTrainer(@RequestParam int trainerName,
                                                @RequestParam String content) {
        return messageService.sendMessageFromUserToTrainer(trainerName, content);
    }

    @PostMapping("/trainerToUser")
    public Message sendMessageFromTrainerToUser(@RequestParam int userUsername,
                                                @RequestParam String content) {
        return messageService.sendMessageFromTrainerToUser(userUsername, content);
    }

    @GetMapping("/history")
    public List<Message> getChatHistory(@RequestParam int userUsername,
                                        @RequestParam int trainerName) {
        return messageService.getChatHistoryBetweenUserAndTrainer(userUsername, trainerName);
    }
}
