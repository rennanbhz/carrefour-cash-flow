package com.act.carrefourcashflow.business.usecase

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.repository.IFinancialPostingRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

import java.time.LocalDate

import static com.act.carrefourcashflow.external.utils.MockFileRequest.createFinancialPosting

@CompileDynamic
class GetFinancialPostingByDateUseCaseTest extends Specification {

    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase
    IFinancialPostingRepository financialPostingRepository = Mock()

    def setup() {
        getFinancialPostingByDateUseCase = new GetFinancialPostingByDateUseCase(financialPostingRepository)
    }

    def "It should get Financial Posting list successfully"() {
        given:
        FinancialPosting financialPosting = createFinancialPosting()
        FinancialPosting financialPosting_2 = createFinancialPosting(
                "6419da6caf1c4f1e7264bcc7",
                "10b1bbff-cd24-4886-a9a4-24c0a2942ac8")

        List<FinancialPosting> financialPosts = List.of(financialPosting, financialPosting_2)


        when:
        List<FinancialPosting> financialPostingResult = getFinancialPostingByDateUseCase.execute(CREATED_AT_2023)

        then:
        1 * financialPostingRepository.findAllByDate(CREATED_AT_2023) >> financialPosts

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
