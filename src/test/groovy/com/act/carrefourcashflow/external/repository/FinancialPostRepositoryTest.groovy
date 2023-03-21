package com.act.carrefourcashflow.external.repository

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository
import com.act.carrefourcashflow.external.db.common.BaseRepositoryTest
import groovy.transform.CompileDynamic
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate

import java.time.LocalDate

import static com.act.carrefourcashflow.external.utils.MockFileRequest.createFinancialPosting

@CompileDynamic
class FinancialPostRepositoryTest extends BaseRepositoryTest {

    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    @Autowired
    private IFinancialPostingRepository financialPostingRepository

    @Autowired
    MongoTemplate mongoTemplate

    @Before
    void setUp() {
        financialPostInserts()
    }

    @After
    void after() {
        financialPostCleanUp()
    }

    @Test
    void "It should Create FinancialPosting with success"() {
        given:
        FinancialPosting financialPosting = createFinancialPosting()

        when:
        FinancialPosting financialPostingResult = financialPostingRepository.save(financialPosting)

        then:
        mongoTemplate.save(financialPosting, "FinancialPosts")
        assert financialPosting.getId() == financialPostingResult.getId()
        assert financialPosting.getValue() == financialPostingResult.getValue()
        assert financialPosting.getTransactionId() == financialPostingResult.getTransactionId()
        assert financialPosting.getType() == financialPostingResult.getType()
        assert financialPosting.getDescription() == financialPostingResult.getDescription()
        assert financialPosting.getCreatedAt() == financialPostingResult.getCreatedAt()
    }

    @Test
    void "It should Find all Financial Posts by date with success"() {
        given:
        FinancialPosting financialPosting = createFinancialPosting()
        FinancialPosting financialPosting_2 = createFinancialPosting()

        when:
        List<FinancialPosting> financialPostingResult = financialPostingRepository.findAllByDate(CREATED_AT_2023)

        then:
        mongoTemplate.save(financialPosting, "FinancialPosts")
        mongoTemplate.save(financialPosting_2, "FinancialPosts")
        assert financialPosting.getValue() == financialPostingResult.get(0).getValue()
        assert financialPosting.getTransactionId() == financialPostingResult.get(0).getTransactionId()
        assert financialPosting.getType() == financialPostingResult.get(0).getType()
        assert financialPosting.getDescription() == financialPostingResult.get(0).getDescription()
        assert financialPosting.getCreatedAt() == financialPostingResult.get(0).getCreatedAt()

        assert financialPosting_2.getValue() == financialPostingResult.get(1).getValue()
        assert financialPosting_2.getTransactionId() == financialPostingResult.get(1).getTransactionId()
        assert financialPosting_2.getType() == financialPostingResult.get(1).getType()
        assert financialPosting_2.getDescription() == financialPostingResult.get(1).getDescription()
        assert financialPosting_2.getCreatedAt() == financialPostingResult.get(1).getCreatedAt()
    }
}
