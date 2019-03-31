package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/** 品牌数据访问接口 */
public interface BrandMapper extends Mapper<Brand> {
    /** 查询全部品牌*/
    List<Brand> findAll(Brand brand);

    /** 动态删除*/
    void deleteAll(Long[] ids);

    @Select("select id, name as text from tb_brand order by id asc")
    List<Map<String,Object>> findAllByIdAndName();
}
