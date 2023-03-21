package com.act.carrefourcashflow.business.controller


import com.act.carrefourcashflow.business.usecase.GenerateReportUseCase
import com.act.carrefourcashflow.business.usecase.GetFinancialPostingByDateUseCase
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDate

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty

@CompileDynamic
class GenerateDailyReportControllerTest extends Specification {

    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    GenerateDailyReportController generateDailyReportController
    GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase = Mock()
    GenerateReportUseCase generateReportUseCase = Mock()

    void setup() {
        generateDailyReportController = new GenerateDailyReportController(getFinancialPostingByDateUseCase,
                generateReportUseCase)
    }

    def "It should generate daily Financial Posting report successfully"() {
        when:
        ResponseEntity<Map<String, Object>> result = generateDailyReportController.generateDailyReport(CREATED_AT_2023)

        then:
        1 * getFinancialPostingByDateUseCase.execute(CREATED_AT_2023)
        1 * generateReportUseCase.execute(CREATED_AT_2023)

        assert isNotEmpty(result)
        assert result.getStatusCode() == HttpStatus.OK
    }
}
