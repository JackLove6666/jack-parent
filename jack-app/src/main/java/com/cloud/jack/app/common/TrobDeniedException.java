/*
 *
 *      Copyright (c) 2018-2025, Aukey IT All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the trob4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Aukey IT (AukeyIT@aukeys.com)
 *
 */

package com.cloud.jack.app.common;

import lombok.NoArgsConstructor;

/**
 * @author Aukey IT
 * @date 2018年06月22日16:22:03
 * 403 授权拒绝
 */
@NoArgsConstructor
public class TrobDeniedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TrobDeniedException(String message) {
		super(message);
	}

	public TrobDeniedException(Throwable cause) {
		super(cause);
	}

	public TrobDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public TrobDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
