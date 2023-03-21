package com.act.carrefourcashflow.external.config

import groovy.transform.CompileDynamic
import spock.lang.Specification

import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

import static java.util.Objects.isNull

@CompileDynamic
class MongoConfigurationTest extends Specification {

    static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    public static final OffsetDateTime OFFSET_DATETIME =
            OffsetDateTime.of(
                    LocalDateTime.of(
                            2020, 3, 5, 8, 15, 20), ZoneOffset.UTC)

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    SimpleDateFormat simpleDateFormat

    void setup() {
        simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    def "It should have custom converters configuration"() {
        given:
        MongoConfiguration configuration = new MongoConfiguration()

        when:
        Boolean targetOffsetDateTime = configuration.customConversions().hasCustomReadTarget(Date.class, OffsetDateTime.class)
        Boolean targetDate = configuration.customConversions().hasCustomWriteTarget(OffsetDateTime.class, Date.class)

        then:
        assert targetDate
        assert targetOffsetDateTime
    }

    def "It should convert Offset to Date"() {
        given:
        OffsetDateTime dateToConvert = OFFSET_DATETIME

        Date dateResult = MongoConfiguration.OffsetDateTimeToDateConverter.INSTANCE.convert(dateToConvert)

        when:
        String dateResultAsString = simpleDateFormat.format(dateResult)
        String originalDateAsString = dateToConvert.format(dateTimeFormatter)

        then:
        assert originalDateAsString == dateResultAsString
    }

    def "It should throw exception when convert Offset to Date"() throws ParseException {
        given:
        String dateResultAsString = "2020-04-01T10:10:02.131Z";
        Date dateToConvert = simpleDateFormat.parse(dateResultAsString)

        when:
        OffsetDateTime offsetDateResult = MongoConfiguration.DateToOffsetDateTimeConverter.INSTANCE.convert(dateToConvert)
        String result = dateTimeFormatter.format(offsetDateResult)

        then:
        assert dateResultAsString == result
    }

    def "It should return null when try to convert null Offset"() {
        when:
        Date dateResult = MongoConfiguration.OffsetDateTimeToDateConverter.INSTANCE.convert(null);

        then:
        assert isNull(dateResult)
    }

    def "It should return null when try to convert null Date"() {
        when:
        OffsetDateTime dateResult = MongoConfiguration.DateToOffsetDateTimeConverter.INSTANCE.convert(null);

        then:
        assert isNull(dateResult)
    }
}

