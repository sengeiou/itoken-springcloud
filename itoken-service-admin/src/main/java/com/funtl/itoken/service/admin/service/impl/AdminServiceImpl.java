package com.funtl.itoken.service.admin.service.impl;


import com.funtl.itoken.common.domain.TbSysUser;
import com.funtl.itoken.common.mapper.TbSysUserMapper;
import com.funtl.itoken.common.service.impl.BaseServiceImpl;
import com.funtl.itoken.service.admin.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/1 16:48
 * @Version 1.0
 **/

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<TbSysUser, TbSysUserMapper> implements AdminService {

}
