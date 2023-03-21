package com.act.carrefourcashflow.external.db.common

import com.act.carrefourcashflow.business.dto.FinancialPosting
import com.act.carrefourcashflow.external.utils.MockFileRequest
import groovy.transform.CompileDynamic
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.MongoDBContainer

import java.time.LocalDate

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [Initializer.class])
@CompileDynamic
abstract class BaseRepositoryTest {

    private static final String COLLECTION_NAME = "FinancialPosts"
    static final String REQUEST_ID_02 = "61545a58423b9c2439e1a43b"
    static final LocalDate CREATED_AT_2023 = LocalDate.of(2023, 12, 12)

    @Autowired
    MongoTemplate mongoTemplate

    public static MongoDBContainer mongoDBContainer =
            new MongoDBContainer()

    static {
        mongoDBContainer.start()
    }

    @Value('http://localhost:${local.server.port}')
    String baseUrl

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.uri=" + mongoDBContainer.getReplicaSetUrl()
            )
                    .applyTo(configurableApplicationContext.getEnvironment())
        }
    }

    void financialPostInserts() {
        FinancialPosting financialPosting = MockFileRequest.createFinancialPosting()
        FinancialPosting financialPosting_2 = MockFileRequest.createFinancialPosting()

        financialPosting_2.setId(REQUEST_ID_02)
        financialPosting_2.setCreatedAt(CREATED_AT_2023)

        mongoTemplate.insert(List.of(financialPosting, financialPosting_2), COLLECTION_NAME)
    }

    void financialPostCleanUp() {
        mongoTemplate.dropCollection(COLLECTION_NAME)
    }
}
