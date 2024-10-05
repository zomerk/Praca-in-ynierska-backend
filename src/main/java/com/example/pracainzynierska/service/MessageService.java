package com.example.pracainzynierska.service;

import com.example.pracainzynierska.entity.Message;
import com.example.pracainzynierska.entity.Trainer;
import com.example.pracainzynierska.entity.User;
import com.example.pracainzynierska.repository.MessageRepository;
import com.example.pracainzynierska.repository.TrainerRepository;
import com.example.pracainzynierska.repository.UserRepository;
import com.example.pracainzynierska.service.adapter.TrainerAdapter;
import com.example.pracainzynierska.service.adapter.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    // Send a message from User to Trainer
    public Message sendMessageFromUserToTrainer(int trainerId, String content) {
        UserAdapter loggedUserAdapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loggedUserAdapter.getUser();
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();

        Message message = new Message();
        message.setUser(user);
        message.setTrainer(trainer);
        message.setContent(content);
        message.setUserSender(true);
        return messageRepository.save(message);
    }

    // Send a message from Trainer to User
    public Message sendMessageFromTrainerToUser(int userId, String content) {
        TrainerAdapter loggedUserAdapter = (TrainerAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Trainer trainer = loggedUserAdapter.getTrainer();
        User user = userRepository.findById(userId).orElseThrow();

        Message message = new Message();
        message.setTrainer(trainer);
        message.setUser(user);
        message.setContent(content);
        message.setUserSender(false);
        return messageRepository.save(message);
    }

    // Get chat history between a User and a Trainer
    public List<Message> getChatHistoryBetweenUserAndTrainer(int  userId, int trainerId) {
        User user = userRepository.findById(userId).orElseThrow();
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();

        List<Message> messages = messageRepository.findByTrainerAndUserAndUserSenderIsFalse(trainer, user);
        messages.addAll(messageRepository.findByUserAndTrainerAndUserSenderIsTrue(user, trainer));

        return messages.stream().sorted(Comparator.comparing(Message::getTimestamp)).collect(Collectors.toList());
    }
}

