package org.softuni.finalpoject.web.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.models.binding.MessageAddBindingModel;
import org.softuni.finalpoject.domain.models.service.LanguageServiceModel;
import org.softuni.finalpoject.domain.models.service.MessageServiceModel;
import org.softuni.finalpoject.domain.models.view.MessageViewModel;
import org.softuni.finalpoject.service.MessageService;
import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("messages")
public class MessageController extends BaseController {

    private final MessageService messageService;
    private final ModelMapper modelMapper;

    @Autowired
    public MessageController(MessageService messageService, ModelMapper modelMapper) {
        this.messageService = messageService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    @PageTitle("Add Message")
    public ModelAndView addMessage(){
        return super.view("message/add-message");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView addMessageConfirm(@ModelAttribute MessageAddBindingModel model, Principal principal){
        MessageServiceModel messageServiceModel = this.modelMapper.map(model, MessageServiceModel.class);
        this.messageService.addMessage(messageServiceModel, principal.getName());

        return super.view("message/thanks-message");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("All Messages")
    public ModelAndView allMessages(ModelAndView modelAndView) {
        List<MessageViewModel> viewModels = messageService.findAllMessages()
                .stream()
                .map(m -> modelMapper.map(m, MessageViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("messages", viewModels);
        return view("message/all-messages", modelAndView);
    }
}
