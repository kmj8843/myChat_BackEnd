package com.rlalsa8843.mychatbe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
public class MongoDBConfiguration {
    private final MongoDatabaseFactory mongoDbFactory;
    private final MongoMappingContext mongoMappingContext;

    @Autowired
    public MongoDBConfiguration(MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext mongoMappingContext) {
        mongoDbFactory = mongoDatabaseFactory;
        this.mongoMappingContext = mongoMappingContext;
    }

    /**
     * MongoDB에 데이터 저장 시 _class 필드가 생기는 것을 방지
     * */
    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

}
