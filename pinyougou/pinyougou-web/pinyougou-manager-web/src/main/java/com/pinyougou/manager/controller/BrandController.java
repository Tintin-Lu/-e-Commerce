package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Brand;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.service.BrandService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
    @Reference(timeout = 20000)
    private BrandService brandService;

    /** 查询全部品牌 */
    @GetMapping("findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

    /** 保存品牌 */
    @RequestMapping("save")
    public boolean save(@RequestBody Brand brand){
        try {
            brandService.save(brand);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /** 修改品牌 */
    @PostMapping("update")
    public boolean update(@RequestBody Brand brand){
        try {
            brandService.update(brand);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /** 分页查询品牌 */
    @GetMapping("findByPage")
    public PageResult findByPage(Brand brand, Integer page, Integer rows){
        if (brand != null && StringUtils.isNoneBlank(brand.getName())){
            try{
                brand.setName(new String(brand.getName().getBytes("ISO8859-1"),"UTF-8"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return brandService.findByPage(brand,page ,rows);
    }

    /** 删除品牌 */
    @GetMapping("delete")
    public boolean delete(Long[] ids){
        try {
            brandService.deleteAll(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}