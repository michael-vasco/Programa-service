package co.edu.uceva.programaservice.domain.services;

import co.edu.uceva.programaservice.domain.repositories.IProgramaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramaServiceImpl implements IProgramaService {

    IProgramaRepository programaRepository;

    public ProgramaServiceImpl(IProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }


    @Override
    @Transactional
    public Programa save(Programa producto) {
        return programaRepository.save(producto);
    }

    @Override
    @Transactional
    public void delete(Programa producto) {
        programaRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Programa> findById(Long id) {
        return programaRepository.findById(id);
    }

    @Override
    @Transactional
    public Programa update(Programa producto) {
        return programaRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Programa> findAll() {
        return (List<Programa>) programaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Programa> findAll(Pageable pageable) {
        return programaRepository.findAll(pageable);
    }
}
