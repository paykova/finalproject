package org.softuni.finalpoject.service;

import org.modelmapper.ModelMapper;
import org.softuni.finalpoject.domain.entities.Instrument;
import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;
import org.softuni.finalpoject.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstrumentServiceImpl implements InstrumentService {

    private final InstrumentRepository instrumentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public InstrumentServiceImpl(InstrumentRepository instrumentRepository, ModelMapper modelMapper) {
        this.instrumentRepository = instrumentRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public InstrumentServiceModel addInstrument(InstrumentServiceModel instrumentServiceModel) {
       Instrument instrument = this.modelMapper.map(instrumentServiceModel, Instrument.class);

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
                .orElseThrow(() -> new IllegalArgumentException());
        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel editInstrument(String id, InstrumentServiceModel instrumentServiceModel) {
        Instrument instrument = this.instrumentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        instrument.setName(instrumentServiceModel.getName());
        return this.modelMapper.map(this.instrumentRepository.saveAndFlush(instrument), InstrumentServiceModel.class);
    }

    @Override
    public InstrumentServiceModel deleteInstrument(String id) {
        Instrument instrument = this.instrumentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        this.instrumentRepository.delete(instrument);
        return this.modelMapper.map(instrument, InstrumentServiceModel.class);
    }
}
