package com.act.carrefourcashflow.business.controller.assembler

import com.act.carrefourcashflow.business.controller.model.FinancialPostingRequest
import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.dto.PostingType
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class FinancialPostingRequestToFinancialPostingAssemblerTest extends Specification {

    FinancialPostingRequestToFinancialPostingAssembler assembler

    def setup() {
        assembler = new FinancialPostingRequestToFinancialPostingAssembler()
    }

    def "It should assemble a FinancialPostingRequest successfully"() {
        given:
        FinancialPostingRequest financialPostingRequest = new FinancialPostingRequest()
        financialPostingRequest.setType(PostingType.CREDIT)
        financialPostingRequest.setDescription("Description of assemble")
        financialPostingRequest.setValue(500)
        financialPostingRequest.setTransactionId("40ec75c3-cf32-4132-8559-f90b02a1dcc8")

        when:
        FinancialPosting financialPosting = assembler.assemble(financialPostingRequest)

        then:
        assert financialPosting.getValue() == financialPostingRequest.getValue()
        assert financialPosting.getDescription() == financialPostingRequest.getDescription()
        assert financialPosting.getType() == financialPostingRequest.getType()
    }
}
