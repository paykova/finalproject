package org.softuni.finalpoject.service;

import org.softuni.finalpoject.domain.models.service.InstrumentServiceModel;

import java.util.List;

public interface InstrumentService {

    InstrumentServiceModel addInstrument (InstrumentServiceModel instrumentServiceModel);

    List<InstrumentServiceModel> findAllInstruments();

    InstrumentServiceModel findInstrumentById(String id);

    InstrumentServiceModel editInstrument(String id, InstrumentServiceModel instrumentServiceModel);

    InstrumentServiceModel deleteInstrument(String id);
}
