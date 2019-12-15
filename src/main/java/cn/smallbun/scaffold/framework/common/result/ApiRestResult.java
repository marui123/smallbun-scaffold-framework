/*
 * Copyright (c) 2018-2019.‭‭‭‭‭‭‭‭‭‭‭‭[zuoqinggang] www.pingfangushi.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package cn.smallbun.scaffold.framework.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static cn.smallbun.scaffold.framework.exception.enums.Exception.EX900001;

/**
 * ApiRestResult
 * @author SanLi
 * Created by qinggang.zuo@gmail.com / 2689170096@qq.com on 2019/5/17 23:44
 */
@ApiModel(value = "API REST 返回")
public class ApiRestResult<T> implements Serializable {
	/**
	 * 成功
	 */
	public static final String SUCCESS = "200";
	/**
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private String status;
	/**
	 * 内容消息
	 */
	@ApiModelProperty(value = "消息")
	private String message;
	/**
	 * 返回内容
	 */
	@ApiModelProperty(value = "返回内容")
	private T result;


	public ApiRestResult() {
	}

	private ApiRestResult(String status, String message, T result) {
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public ApiRestResult<T> result(T result) {
		this.result = result;
		return this;
	}

	public T getResult() {
		return result;
	}

	public String getStatus() {
		if (StringUtils.isBlank(status)) {
			return String.valueOf(HttpStatus.OK.value());
		}
		return status;
	}

	public ApiRestResult<T> status(String status) {
		this.status = status;
		return this;
	}

	public String getMessage() {
		if (StringUtils.isBlank(message)) {
			return "操作成功";
		}
		return message;
	}

	public ApiRestResult<T> message(String msg) {
		this.message = msg;
		return this;
	}

	/**
	 **
	 * 成功
	 *
	 * @param data 响应结果
	 * @return 结果
	 */
	public static <T> ApiRestResult<T> ok(T data) {
		return new ApiRestResult<>(SUCCESS, "操作成功", data);
	}

	/**
	 * 成功 data null
	 *
	 * @return 成功结果
	 */
	public static <T> ApiRestResult<T> ok() {
		return new ApiRestResult<>(SUCCESS, "操作成功", null);
	}

	/**
	 * 默认服务器内部错误
	 *
	 * @return 返回结果
	 */
	public static <T> ApiRestResult<T> err() {
		return new ApiRestResult<>(EX900001.getCode(), EX900001.getMessage(), null);
	}

	/**
	 * 默认服务器内部错误
	 *
	 * @return 返回结果
	 */
	public static <T> ApiRestResult<T> err(T data) {
		return new ApiRestResult<>(EX900001.getCode(), EX900001.getMessage(), data);
	}

	/**
	 * 自定义 错误码和错误信息
	 *
	 * @param msg  错误信息
	 * @param code 错误码
	 * @return 返回结果
	 */
	public static <T> ApiRestResult<T> err(String msg, String code) {
		return new ApiRestResult<>(code, msg, null);
	}

	@Override
	public String toString() {
		return "ApiRestResult{" + "result=" + result + ", status='" + status + '\'' + ", message='" + message + '\''
				+ "} " + super.toString();
	}

	public ApiRestResult<T> build() {
		return new ApiRestResult<>(this.status, this.message, this.result);
	}
}