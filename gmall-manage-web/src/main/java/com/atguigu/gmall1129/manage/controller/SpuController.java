package com.atguigu.gmall1129.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall1129.bean.SpuImage;
import com.atguigu.gmall1129.bean.SpuInfo;
import com.atguigu.gmall1129.bean.SpuSaleAttr;
import com.atguigu.gmall1129.service.ManageService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @param
 * @return
 */

@Controller
public class SpuController {

    @Reference
    ManageService manageService;

    @GetMapping("spuListPage")
    public String spuListPage(){
        return "spuListPage";
    }

    @GetMapping("spuList")
    @ResponseBody
    public String spuList(HttpServletRequest request){
        String catalog3Id = request.getParameter("catalog3Id");
        List<SpuInfo> spuInfoList= manageService.getSpuList(catalog3Id);
        String spuJson = JSON.toJSONString(spuInfoList);
        return spuJson;
    }

    @PostMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(SpuInfo spuInfo){
        manageService.saveSpuInfo(spuInfo);
        return "success";
    }


    @GetMapping("saleAttrListForSku")
    @ResponseBody
    public List<SpuSaleAttr> getSaleAttrList(HttpServletRequest request){
        String spuId = request.getParameter("spuId");
        List<SpuSaleAttr> saleAttrList = manageService.getSaleAttrList(spuId);
        return saleAttrList;
    }


    @GetMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> getSpuImageList(HttpServletRequest request){
        String spuId = request.getParameter("spuId");
        List<SpuImage> spuImageList= manageService.getSpuImageList(spuId);
        return spuImageList;
    }
}
