package Config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

    public class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {

        @Override
        public String serialize(Object dataFetcherResult) {
            if (dataFetcherResult instanceof LocalDateTime) {
                return ((LocalDateTime) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } else {
                throw new CoercingSerializeException("Expected a LocalDateTime object.");
            }
        }

        @Override
        public LocalDateTime parseValue(Object input) {
            if (input instanceof String) {
                try {
                    return LocalDateTime.parse((String) input, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (DateTimeParseException e) {
                    throw new CoercingParseValueException("Failed to parse LocalDateTime string", e);
                }
            } else {
                throw new CoercingParseValueException("Expected a String");
            }
        }

        @Override
        public LocalDateTime parseLiteral(Object input) {
            if (input instanceof StringValue) {
                try {
                    return LocalDateTime.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                } catch (DateTimeParseException e) {
                    throw new CoercingParseLiteralException("Failed to parse LocalDateTime string", e);
                }
            } else {
                throw new CoercingParseLiteralException("Expected a StringValue");
            }
        }
    }


