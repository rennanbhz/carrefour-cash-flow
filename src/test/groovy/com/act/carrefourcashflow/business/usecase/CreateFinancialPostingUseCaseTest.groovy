package com.act.carrefourcashflow.business.usecase

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

import static com.act.carrefourcashflow.external.utils.MockFileRequest.createFinancialPosting

@CompileDynamic
class CreateFinancialPostingUseCaseTest extends Specification {

    CreateFinancialPostingUseCase createFinancialPostingUseCase
    IFinancialPostingRepository financialPostRepository = Mock()

    def setup() {
        createFinancialPostingUseCase = new CreateFinancialPostingUseCase(financialPostRepository)
    }

    def "It should create a Financial Posting successfully"() {
        given:
        FinancialPosting financialPosting = createFinancialPosting()

        when:
        FinancialPosting financialPostingResult = createFinancialPostingUseCase.execute(financialPosting)

        then:
        1 * financialPostRepository.save(financialPosting)
        assert financialPostingResult.getId() == financialPosting.getId()
        assert financialPostingResult.getTransactionId() == financialPosting.getTransactionId()
        assert financialPostingResult.getType() == financialPosting.getType()
        assert financialPostingResult.getValue() == financialPosting.getValue()
        assert financialPostingResult.getDescription() == financialPosting.getDescription()
        assert financialPostingResult.getCreatedAt() == financialPosting.getCreatedAt()
    }
}
