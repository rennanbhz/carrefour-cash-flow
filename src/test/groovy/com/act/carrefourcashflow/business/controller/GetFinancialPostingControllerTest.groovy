package com.act.carrefourcashflow.business.controller

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.usecase.GetFinancialPostingByDateUseCase
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.LocalDate

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty

@CompileDynamic
class GetFinancialPostingControllerTest extends Specification {

    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    GetFinancialPostingController getFinancialPostingController
    GetFinancialPostingByDateUseCase getFinancialPostingByDateUseCase = Mock()

    void setup() {
        getFinancialPostingController = new GetFinancialPostingController(getFinancialPostingByDateUseCase)
    }

    def "It should generate daily Financial Posting report successfully"() {
        when:
        ResponseEntity<List<FinancialPosting>> result = getFinancialPostingController.getFinancialPost(CREATED_AT_2023)

        then:
        1 * getFinancialPostingByDateUseCase.execute(CREATED_AT_2023)

        assert isNotEmpty(result)
        assert result.getStatusCode() == HttpStatus.OK
    }
}
