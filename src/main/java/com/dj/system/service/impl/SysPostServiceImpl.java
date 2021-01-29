package com.dj.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.common.QuerySysPostRequest;
import com.dj.common.ResEntity;
import com.dj.system.mapper.SysPostMapper;
import com.dj.system.mapper.SysUserPostMapper;
import com.dj.system.model.SysPostEntity;
import com.dj.system.model.SysUserPostEntity;
import com.dj.system.service.SysPostService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jcj
 * @date 2020-03-20 上午10:23
 * @company www.dfdk.com.cn
 */
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPostEntity> implements SysPostService {
    @Autowired
    private SysUserPostMapper sysUserPostMapper;

    /**
     *
    * 按岗位名称来进行查询
    * */
    @Override
    public SysPostEntity getByPostName(String postName) {
        QueryWrapper<SysPostEntity> postEntityQueryWrapper = new QueryWrapper<>();
        postEntityQueryWrapper.lambda().eq(SysPostEntity::getPostName,postName);
        return this.baseMapper.selectOne(postEntityQueryWrapper);
    }

    /**
     * 删除岗位
     *
     * @param postId
     * @return
     */
    @Override
    public ResEntity<?> removeByPostId(Long postId) {
        //先查询岗位用户中间表是否有关联数据
        Integer count = sysUserPostMapper.selectCount(new LambdaQueryWrapper<SysUserPostEntity>().eq(SysUserPostEntity::getPostId, postId));
        if (count != 0){
            return ResEntity.ERROR("该岗位已分配，不能删除");
        }
        this.baseMapper.deleteById(postId);
        return ResEntity.SUCCESS("删除成功");
    }

    @Override
    public List<T> queryPostList(){
       return baseMapper.queryPostList();
    }

    @Override
    public ResEntity<?> querySysPostPage(QuerySysPostRequest request) {
        IPage<SysPostEntity> page = new Page<>(request.getCurrentPage(), request.getPageSize());
        LambdaQueryWrapper<SysPostEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(request.getPostName()), SysPostEntity::getPostName, request.getPostName());
        IPage<SysPostEntity> selectPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        return ResEntity.OK(selectPage.getRecords(), Integer.parseInt(selectPage.getTotal() + ""));
    }
}
