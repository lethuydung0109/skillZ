package miage.skillz.service;

import miage.skillz.entity.Niveau;
import miage.skillz.enumeration.ENiveau;
import miage.skillz.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NiveauService {
    @Autowired
    private NiveauRepository niveauRepository;
    public Optional<Niveau> findByName(ENiveau niveau) {
        return niveauRepository.findByNiveau(niveau);
    }
}
