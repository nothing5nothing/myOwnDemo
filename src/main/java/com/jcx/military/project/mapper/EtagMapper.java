package com.jcx.military.project.mapper;

import com.jcx.military.project.data.Etag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EtagMapper {


    List<Etag> selectAll(@Param("id") String id);


}
