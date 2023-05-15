package org.example.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/** @Description 分页请求必须继承该类 */
@ApiModel(value = "分页请求")
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {

  @ApiModelProperty(value = "当前页数")
  private Integer pageNo = 1;

  @ApiModelProperty(value = "每页数量")
  private Integer pageSize = 10;

  public Integer getPageNo() {
    return (pageNo == null || pageNo <= 0) ? 1 : pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    return (pageSize == null || pageSize <= 0) ? 10 : pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
