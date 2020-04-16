package com.yangbingdong.messagenoloss.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface SourceTargetMapper {

    SourceTargetMapper SOURCE_TARGET_MAPPER = Mappers.getMapper( SourceTargetMapper.class );

    @Mapping( source = "test", target = "testing" )
    Target toTarget(Source s);

    @Mapping( source = "test", target = "testing", nullValuePropertyMappingStrategy = IGNORE)
    void update(Source s, @MappingTarget Target t);
}
