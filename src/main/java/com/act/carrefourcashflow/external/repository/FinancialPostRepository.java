package com.act.carrefourcashflow.external.repository;

import com.act.carrefourcashflow.business.dto.FinancialPosting;
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class FinancialPostRepository implements IFinancialPostingRepository {

  private static final String COLLECTION_NAME = "FinancialPosts";
  private static final String DATE = "createdAt";
  private static final Logger LOGGER = LoggerFactory.getLogger(FinancialPostRepository.class);

  private final MongoTemplate mongoTemplate;

  public FinancialPostRepository(MongoTemplate mongoTemplate) {

    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public FinancialPosting save(FinancialPosting financialPosting) {
    LOGGER.info(
        "Started FinancialPostRepository.save with TransactionId {}",
        financialPosting.getTransactionId());
    return mongoTemplate.save(financialPosting, COLLECTION_NAME);
  }

  @Override
  public List<FinancialPosting> findAllByDate(LocalDate date) {
    LOGGER.info("Started FinancialPostRepository.findAllByDate");
    final Query query = new Query();
    query.limit(10);
    query.addCriteria(Criteria.where(DATE).is(date)).with(Sort.by(Sort.Order.desc(DATE)));
    return mongoTemplate.find(query, FinancialPosting.class, COLLECTION_NAME);
  }
}
