package org.thales.transaction.model.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.thales.transaction.model.domain.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  Optional<Transaction> getTransactionById(final Long id);

  List<Transaction> findTransactionsByTypeAndActor(final String type, final String actor);
}
