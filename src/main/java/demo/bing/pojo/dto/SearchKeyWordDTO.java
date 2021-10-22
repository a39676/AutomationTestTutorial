package demo.bing.pojo.dto;

import io.swagger.annotations.ApiModelProperty;

public class SearchKeyWordDTO {

	private String keyword;

	@ApiModelProperty(value = "keyword of searching", example = "zhang3.xyz", required = true)
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
