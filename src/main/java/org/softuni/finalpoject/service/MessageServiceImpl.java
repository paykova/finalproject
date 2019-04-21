package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Message;
import org.softuni.finalpoject.domain.entities.User;
import org.softuni.finalpoject.domain.models.service.MessageServiceModel;
import org.softuni.finalpoject.repository.MessageRepository;
import org.softuni.finalpoject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public MessageServiceModel addMessage(MessageServiceModel messageServiceModel, String name) {

        Message message = this.modelMapper.map(messageServiceModel, Message.class);

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found!"));

        message.setAuthor(user);

        try {
            message = this.messageRepository.saveAndFlush(message);
            return this.modelMapper.map(message, MessageServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<MessageServiceModel> findAllMessages() {
        return this.messageRepository.findAll()
                .stream()
                .map(m -> this.modelMapper.map(m, MessageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public MessageServiceModel findMessageById(String id) throws NotFoundException {

        Message message = this.messageRepository.findById(id).orElse(null);
        if (message == null) {
            throw new IllegalArgumentException("Message not found!");
        }
        return this.modelMapper.map(message, MessageServiceModel.class);
    }


    @Override
    public MessageServiceModel editMassage(String id, MessageServiceModel messageServiceModel) {

        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Message not found!"));

        message.setId(messageServiceModel.getId());
        return this.modelMapper.map(this.messageRepository.saveAndFlush(message), MessageServiceModel.class);
    }


    @Override
    public MessageServiceModel deleteMessage(String id) {

        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Message not found!"));

        this.messageRepository.delete(message);
        return this.modelMapper.map(message, MessageServiceModel.class);
    }
}
