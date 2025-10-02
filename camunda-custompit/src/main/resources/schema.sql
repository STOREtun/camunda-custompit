-- public.kafka_messages definition

-- Drop table

-- DROP TABLE kafka_messages;

CREATE TABLE kafka_messages (
                                business_key varchar NULL,
                                created timestamp NULL,
                                topic varchar NULL,
                                id integer auto_increment,
                                CONSTRAINT kafka_messages_pk PRIMARY KEY (id)
);
CREATE INDEX kafka_messages_business_key_idx ON public.kafka_messages USING btree (business_key);