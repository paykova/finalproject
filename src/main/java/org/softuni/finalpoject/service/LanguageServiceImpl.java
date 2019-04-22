package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.constants.Constants;
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
    public LanguageServiceImpl(LanguageRepository languageRepository,
                               ModelMapper modelMapper,
                               Validator validator) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public LanguageServiceModel addLanguage(LanguageServiceModel languageServiceModel) {

        if(!validator.validate(languageServiceModel).isEmpty()){
            throw new IllegalArgumentException(Constants.INVALID_LANGUAGE_ERROR_MESSAGE);
        }

        Language language = this.modelMapper.map(languageServiceModel, Language.class);

        if (this.languageRepository.findByName(language.getName()).orElse(null) != null) {
            throw new IllegalArgumentException(Constants.LANGUAGE_EXISTS_ERROR_MESSAGE);
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
                .orElseThrow(() -> new IllegalArgumentException(Constants.LANGUAGE_NOT_FOUND_ERROR_MESSAGE));

        return this.modelMapper.map(language, LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel editLanguage(String id, LanguageServiceModel languageServiceModel) {

        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.LANGUAGE_NOT_FOUND_ERROR_MESSAGE));

        language.setName(languageServiceModel.getName());

        if (this.languageRepository.findByName(language.getName()).orElse(null) != null) {
            throw new IllegalArgumentException(Constants.LANGUAGE_EXISTS_ERROR_MESSAGE);
        }

        return this.modelMapper.map(this.languageRepository.saveAndFlush(language), LanguageServiceModel.class);
    }

    @Override
    public LanguageServiceModel deleteLanguage(String id) {

        Language language = this.languageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(Constants.LANGUAGE_NOT_FOUND_ERROR_MESSAGE));

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
