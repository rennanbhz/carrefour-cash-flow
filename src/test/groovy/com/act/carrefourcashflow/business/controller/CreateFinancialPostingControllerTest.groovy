package com.act.carrefourcashflow.business.controller

import com.act.carrefourcashflow.business.controller.assembler.FinancialPostingRequestToFinancialPostingAssembler
import com.act.carrefourcashflow.business.controller.model.FinancialPostingRequest
import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.dto.PostingType
import com.act.carrefourcashflow.business.usecase.CreateFinancialPostingUseCase
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty

@CompileDynamic
class CreateFinancialPostingControllerTest extends Specification {

    CreateFinancialPostingController createFinancialPostingController
    CreateFinancialPostingUseCase createFinancialPostingUseCase = Mock()
    FinancialPostingRequestToFinancialPostingAssembler financialPostingRequestToFinancialPostingAssembler = Mock()

    void setup() {
        createFinancialPostingController = new CreateFinancialPostingController(createFinancialPostingUseCase,
                financialPostingRequestToFinancialPostingAssembler)
    }

    def "It should create Financial Posting successfully"() {
        when:
        FinancialPostingRequest financialPostingRequest = new FinancialPostingRequest()
        financialPostingRequest.setTransactionId("001")
        financialPostingRequest.setType(PostingType.CREDIT)
        financialPostingRequest.setValue(500)
        financialPostingRequest.setDescription("Test")

        ResponseEntity<FinancialPosting> result = createFinancialPostingController.createFinancialPost(financialPostingRequest)

        then:
        1 * createFinancialPostingUseCase.execute(_)
        1 * financialPostingRequestToFinancialPostingAssembler.assemble(_)

        assert isNotEmpty(result)
        assert result.getStatusCode() == HttpStatus.CREATED
    }
}
