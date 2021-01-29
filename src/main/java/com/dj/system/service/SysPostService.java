package com.dj.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.common.QuerySysPostRequest;
import com.dj.common.ResEntity;
import com.dj.system.model.SysPostEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author jcj
 *
 * @date 2020-03-19 上午10:21
 * @company www.dfdk.com.cn
 */
public interface SysPostService extends IService<SysPostEntity> {

    SysPostEntity getByPostName(String postName);

    ResEntity<?> removeByPostId(Long postId);

    List<T> queryPostList();

    /**
     * 分页查询岗位
     * @param request
     * @return
     */
    ResEntity<?> querySysPostPage(QuerySysPostRequest request);
}
