package com.handsUp.ego_v2.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.handsUp.ego_v2.entity.AddressBook;
import com.handsUp.ego_v2.mapper.AddressBookMapper;
import com.handsUp.ego_v2.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @Author: Xuuxxi
 * @Date: 2022/5/14
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
