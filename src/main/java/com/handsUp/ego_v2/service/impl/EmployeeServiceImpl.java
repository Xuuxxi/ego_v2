package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.Employee;
import com.handsUp.ego_v2.mapper.EmployeeMapper;
import com.handsUp.ego_v2.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/3
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
