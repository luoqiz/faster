package org.wolf.common.entity.http;

import lombok.Data;

@Data
public class PageResponse<k, v> {

	private k total;
	private v data;

	public PageResponse() {
	}

	public PageResponse(k total, v data) {
		this.total = total;
		this.data = data;
	}
}
