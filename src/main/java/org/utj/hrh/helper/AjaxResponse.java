package org.utj.hrh.helper;

import lombok.Data;

@Data
public class AjaxResponse {

		private boolean success;
		private String message;
		private Object data;

}
