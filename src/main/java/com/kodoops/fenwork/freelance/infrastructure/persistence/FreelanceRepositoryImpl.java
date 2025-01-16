package com.kodoops.fenwork.freelance.infrastructure.persistence;

import com.kodoops.fenwork.freelance.domain.exception.FreelanceException;
import com.kodoops.fenwork.freelance.domain.model.Freelance;
import com.kodoops.fenwork.freelance.domain.repository.FreelanceRepository;
import com.kodoops.fenwork.freelance.infrastructure.mapper.FreelanceMapper;
import com.kodoops.fenwork.freelance.infrastructure.persistence.entity.FreelanceEntity;
import com.kodoops.fenwork.freelance.infrastructure.persistence.jpa.JpaFreelanceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FreelanceRepositoryImpl implements FreelanceRepository {

    private final JpaFreelanceRepository jpaFreelanceRepository;

    public FreelanceRepositoryImpl(JpaFreelanceRepository jpaFreelanceRepository) {
        this.jpaFreelanceRepository = jpaFreelanceRepository;
    }

    @Override
    public Freelance save(Freelance freelance) {

       FreelanceEntity saved= jpaFreelanceRepository.save(FreelanceMapper.toEntity(freelance));
        return FreelanceMapper.toDomain(saved);
    }

    @Override
    public Optional<Freelance> findById(String id) {
        Optional<FreelanceEntity> freelanceEntity = jpaFreelanceRepository.findById(id);

        return freelanceEntity.map(FreelanceMapper::toDomain);
    }

    @Override
    public List<Freelance> findAll() {
        List<FreelanceEntity> freelances  = jpaFreelanceRepository.findAll();
        return freelances.stream().map(FreelanceMapper::toDomain).toList();
    }

    @Override
    public void deleteFreelance(String id) {
        Optional<FreelanceEntity> freelanceEntity = jpaFreelanceRepository.findById(id);
        if(freelanceEntity.isEmpty())
            throw new FreelanceException("Freelance introuvable dnas nos archives.");
        jpaFreelanceRepository.deleteById(id);
    }
}
