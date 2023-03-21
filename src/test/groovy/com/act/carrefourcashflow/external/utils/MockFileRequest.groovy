package com.act.carrefourcashflow.external.utils

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.business.dto.PostingType

import java.time.LocalDate

class MockFileRequest {

    static FinancialPosting createFinancialPosting(String objectId = "6419da5daf1c4f1e7264bcc5",
                                                   String transactionId = "a8a381cc-077e-406b-81e0-7327f10d7b15",
                                                   PostingType type = PostingType.CREDIT,
                                                   String description = "test",
                                                   LocalDate createdAt = LocalDate.of(2023, 12, 12),
                                                   double value = 400) {
        def financialPosting = new FinancialPosting()
        financialPosting.setId(objectId)
        financialPosting.setTransactionId(transactionId)
        financialPosting.setType(type)
        financialPosting.setDescription(description)
        financialPosting.setCreatedAt(createdAt)
        financialPosting.setValue(value)

        return financialPosting
    }
}