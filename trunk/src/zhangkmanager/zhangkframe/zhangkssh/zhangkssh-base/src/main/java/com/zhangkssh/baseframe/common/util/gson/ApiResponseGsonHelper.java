// Copyright 2012 Citrix Systems, Inc. Licensed under the
// Apache License, Version 2.0 (the "License"); you may not use this
// file except in compliance with the License.  Citrix Systems, Inc.
// reserves all rights not expressly granted by the License.
// You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// 
// Automatically generated by addcopyright.py at 04/03/2012
package com.zhangkssh.baseframe.common.util.gson;

import java.lang.reflect.Modifier;

import com.google.gson.GsonBuilder;

/**
 * The ApiResonseGsonHelper is different from ApiGsonHelper - it registeres one
 * more  adapter for String type required for api response encoding
 */
public class ApiResponseGsonHelper {
	private static final GsonBuilder s_gBuilder;

	static {
		s_gBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
		s_gBuilder.setVersion(1.3);
		// 不转换没有 @Expose 注解的字段
		s_gBuilder.excludeFieldsWithoutExposeAnnotation();
		//不含带有transient关键字的字段
		s_gBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
	}

	public static GsonBuilder getBuilder() {
		return s_gBuilder;
	}
}
