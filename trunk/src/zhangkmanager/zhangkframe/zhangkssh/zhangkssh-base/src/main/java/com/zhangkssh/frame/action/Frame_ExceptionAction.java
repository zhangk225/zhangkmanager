package com.zhangkssh.frame.action;

import org.springframework.stereotype.Controller;

import com.zhangkssh.externalservice.action.BaseExceptionAction;
import com.zhangkssh.frame.util.json.Frame_ErrorResponse;

@Controller("frame_exception")
public class Frame_ExceptionAction extends BaseExceptionAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() {
		Frame_ErrorResponse s = new Frame_ErrorResponse(getException()
				.getErrorCode(), getException().getErrorText());
		jsonMap.put("errorresponse", s);
		return SUCCESS;
	}

}
