package com.zhuguozhu.framework;

import com.zhuguozhu.framework.helper.AopHelper;
import com.zhuguozhu.framework.helper.BeanHelper;
import com.zhuguozhu.framework.helper.ClassHelper;
import com.zhuguozhu.framework.helper.ControllerHelper;
import com.zhuguozhu.framework.helper.IocHelper;
import com.zhuguozhu.framework.util.ClassUtil;

/**
 * 统一加载相应的Helper类
 * @author Guozhu Zhu
 * @date 2019/07/12
 * @version 1.0
 *
 */
public final class HelperLoader {
    
	public static void init() {
		Class<?>[] classList = {
				ClassHelper.class,
				BeanHelper.class,
				AopHelper.class,
				IocHelper.class,
				ControllerHelper.class
		};
		// ClassUtil.loadClass(IocHelper.class.getName(), true);
		System.out.println("[info]::HelperLoader" + classList.length);
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName(), true);
		}
	}
	
}
