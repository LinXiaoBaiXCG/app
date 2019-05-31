package com.lcq.app.modules.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcq.app.common.exception.CustomException;
import com.lcq.app.modules.app.controller.vo.VwFollowVO;
import com.lcq.app.modules.app.entity.VwFollow;
import com.lcq.app.modules.app.service.VwFollowService;
import com.lcq.app.modules.system.controller.vo.ResultVO;
import com.lcq.app.utils.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @program: app
 * @description:VwFollowController
 * @author: LCQ
 * @create: 2019-05-20 21:50
 **/
@RestController
@RequestMapping("/app/vw/follow")
@Slf4j
public class VwFollowController {
    private final VwFollowService vwFollowService;

    public VwFollowController(VwFollowService vwFollowService) {
        this.vwFollowService = vwFollowService;
    }

    @ApiOperation("添加关注")
    @PostMapping("sava")
    public ResultVO sava(@RequestBody VwFollowVO vwFollowVO){
        ValidatorUtils.validateEntity(vwFollowVO);
        ResultVO resultVO = new ResultVO();
        VwFollow vwFollow = new VwFollow();
        BeanUtils.copyProperties(vwFollowVO,vwFollow);
        Boolean flag = vwFollowService.save(vwFollow);
        if (flag){
            resultVO.setCode(0);
            resultVO.setMsg("添加关注成功");
        }else
            throw new CustomException("添加关注失败");
        return resultVO;
    }

    @ApiOperation("我的关注列表")
    @GetMapping("page")
    public ResultVO page(@RequestParam String userId, @RequestParam int current, @RequestParam int size){
        ResultVO resultVO = new ResultVO();
        Page<VwFollow> page = new Page<>(current,size);
        try {
        IPage<VwFollow> vwFollowIPage = vwFollowService.getPageByUserId(page,userId);
        resultVO.setCode(0);
        resultVO.setMsg("获取我的关注列表成功");
        resultVO.setData(vwFollowIPage);
        }catch (Exception e){
        e.printStackTrace();
        throw new CustomException("获取我的关注列表失败");
    }
        return resultVO;
    }
}
