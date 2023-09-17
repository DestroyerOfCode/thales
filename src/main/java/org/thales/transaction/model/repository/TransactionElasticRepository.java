package org.thales.transaction.model.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.thales.transaction.dto.TransactionDTO;

@Repository
public interface TransactionElasticRepository
    extends ElasticsearchRepository<TransactionDTO, Long> {}
