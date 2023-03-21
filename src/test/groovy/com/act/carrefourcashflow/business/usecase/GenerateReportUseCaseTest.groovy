package com.act.carrefourcashflow.business.usecase

import com.act.carrefourcashflow.business.dto.FinancialPosting
import groovy.transform.CompileDynamic
import spock.lang.Specification

import java.time.LocalDate

import static com.act.carrefourcashflow.external.utils.MockFileRequest.createFinancialPosting

@CompileDynamic
class GenerateReportUseCaseTest extends Specification {

    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    GenerateReportUseCase generateReportUseCase
    GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase = Mock()

    def setup() {
        generateReportUseCase = new GenerateReportUseCase(getFinancialPostingByDateUseCase)
    }

    def "It should genereta Financial Posting Daily report successfully"() {
        given:
        FinancialPosting financialPosting = createFinancialPosting()

        List<FinancialPosting> financialPosts = List.of(financialPosting)


        when:
        double financialPostingResult = generateReportUseCase.execute(CREATED_AT_2023)

        then:
        1 * getFinancialPostingByDateUseCase.execute(CREATED_AT_2023) >> financialPosts
        assert financialPostingResult == financialPosting.getValue()
    }
}
