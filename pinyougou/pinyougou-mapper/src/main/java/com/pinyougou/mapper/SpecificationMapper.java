package com.pinyougou.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.pinyougou.pojo.Specification;

import java.util.List;

/**
 * SpecificationMapper 数据访问接口
 * @date 2019-03-27 23:27:29
 * @version 1.0
 */
public interface SpecificationMapper extends Mapper<Specification>{

    List<Specification> findAll(Specification specification);

    void save(Specification specification);
}