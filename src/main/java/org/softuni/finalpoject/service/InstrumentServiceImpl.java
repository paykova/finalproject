package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.domain.models.view.InstrumentViewModel;
import org.softuni.finalpoject.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository, ModelMapper modelMapper, Validator validator) {
        this.instrumentRepository = instrumentRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public InstrumentServiceModel addInstrument(InstrumentServiceModel instrumentServiceModel) {

        if(!validator.validate(instrumentServiceModel).isEmpty()){
            throw new IllegalArgumentException("Invalid Instrument!");
        }
        Instrument instrument = this.modelMapper.map(instrumentServiceModel, Instrument.class);

        if (this.instrumentRepository.findByName(instrument.getName()).orElse(null) != null) {
            throw new IllegalArgumentException("Instrument with this name already exists!");
        }

        return this.modelMapper.map(this.instrumentRepository.saveAndFlush(instrument), InstrumentServiceModel.class);
    }


    @Override
    public List<InstrumentServiceModel> findAllInstruments() {

        return this.instrumentRepository.findAll()
                .stream().map(i -> this.modelMapper.map(i, InstrumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public InstrumentServiceModel findInstrumentById(String id) {
        Instrument instrument = this.instrumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found!"));

        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel editInstrument(String id, InstrumentServiceModel instrumentServiceModel) {

        Instrument instrument = this.instrumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found!"));

        instrument.setName(instrumentServiceModel.getName());

        if (this.instrumentRepository.findByName(instrument.getName()).orElse(null) != null) {
            throw new IllegalArgumentException("Instrument with this name already exists!");
        }
        return this.modelMapper.map(this.instrumentRepository.saveAndFlush(instrument), InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel deleteInstrument(String id) {

        Instrument instrument = this.instrumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Instrument not found!"));

        this.instrumentRepository.delete(instrument);

        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }

    @Override
    public List<InstrumentViewModel> getInstrumentNames() {
        List<InstrumentViewModel> result;
        result = findAllInstruments()
                .stream()
                .map(i -> this.modelMapper.map(i, InstrumentViewModel.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<InstrumentServiceModel> getInstrumentsByIds(List<String> ids) {

        List<InstrumentServiceModel> result = new ArrayList<>();

        for (String id : ids) {
            InstrumentServiceModel model = findInstrumentById(id);
            result.add(model);
        }
        return result;
    }
}
