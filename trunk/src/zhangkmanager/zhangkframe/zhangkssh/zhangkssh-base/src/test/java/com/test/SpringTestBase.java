package com.test;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.utils.TestConstants;
import com.zhangkssh.frame.service.tools.Frame_DateTools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { TestConstants.APPLICATIONCONTEXT_PATH })
public class SpringTestBase {

	@Autowired
	protected Frame_DateTools dateTools;
}
