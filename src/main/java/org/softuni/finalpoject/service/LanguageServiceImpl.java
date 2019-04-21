package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Language;
import org.softuni.finalpoject.domain.models.service.OtherActivityServiceModel;
import org.softuni.finalpoject.domain.models.view.LanguageViewModel;
import org.softuni.finalpoject.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softuni.finalpoject.domain.models.service.LanguageServiceModel;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper, Validator validator) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public LanguageServiceModel addLanguage(LanguageServiceModel languageServiceModel) {

        if(!validator.validate(languageServiceModel).isEmpty()){
            throw new IllegalArgumentException("Invalid Language!");
        }
        Language language = this.modelMapper.map(languageServiceModel, Language.class);

        if (this.languageRepository.findByName(language.getName()).orElse(null) != null) {
            throw new IllegalArgumentException("Language with this name already exists!");
        }

        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageServiceModel.class);
    }

    @Override
    public List<LanguageServiceModel> findAllLanguages() {
        return this.languageRepository.findAll()
                .stream()
                .map(l -> this.modelMapper.map(l, LanguageServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public LanguageServiceModel findLanguageById(String id) {

        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Language not found!"));

        return this.modelMapper.map(language, LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel editLanguage(String id, LanguageServiceModel languageServiceModel) {

        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Language not found!"));

        language.setName(languageServiceModel.getName());

        if (this.languageRepository.findByName(language.getName()).orElse(null) != null) {
            throw new IllegalArgumentException("Language with this name already exists!");
        }

        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel deleteLanguage(String id) {

        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Language not found!"));

        this.languageRepository.delete(language);

        return this.modelMapper.map(language, LanguageServiceModel.class);
    }

    @Override
    public List<LanguageViewModel> getLanguagesNames() {

        List<LanguageViewModel> result;
        result = findAllLanguages().stream()
                .map(l -> this.modelMapper.map(l, LanguageViewModel.class))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<LanguageServiceModel> getLanguagesByIds(List<String> ids) {

        List<LanguageServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            LanguageServiceModel model = findLanguageById(id);
            result.add(model);
        }
        return result;
    }
}
