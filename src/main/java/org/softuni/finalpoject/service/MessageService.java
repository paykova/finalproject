package org.softuni.finalpoject.service;

import javassist.NotFoundException;
import org.softuni.finalpoject.domain.models.service.MessageServiceModel;

import java.util.List;

public interface MessageService {

    MessageServiceModel addMessage(MessageServiceModel messageServiceModel, String name);

    List<MessageServiceModel> findAllMessages();

    MessageServiceModel findMessageById(String id) throws NotFoundException;

    MessageServiceModel editMassage(String id, MessageServiceModel messageServiceModel);

    MessageServiceModel deleteMessage(String id);
}
