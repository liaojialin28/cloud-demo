package org.example.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/** @Description 分页返回 */
@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页需要用的数据")
@Deprecated
public class PageResponse<T> {
  public PageResponse(Integer pageNo, Integer pageSize) {
    if (pageNo != null && pageNo > 0) {
      this.pageNo = pageNo;
    }
    if (pageSize != null && pageSize > 0) {
      this.pageSize = pageSize;
    }
  }

  @ApiModelProperty(value = "当前页数")
  private Integer pageNo = 1;

  @ApiModelProperty(value = "每页数量")
  private Integer pageSize = 10;

  @ApiModelProperty(value = "总条数")
  private Long totalCount = 0L;

  @ApiModelProperty(value = "总页数")
  private Integer totalPage = 0;

  @ApiModelProperty(value = "当前页数据")
  private List<T> data = new ArrayList<>();

  public static <T> PageResponse<T> of(List<T> list, PageRequest pageRequest, Long totalCount) {
    PageResponse<T> pageResponse = new PageResponse<>();
    pageResponse.setData(list);
    pageResponse.setPageNo(pageRequest.getPageNo());
    pageResponse.setPageSize(pageRequest.getPageSize());
    pageResponse.setTotalCount(totalCount);
    pageResponse.setTotalPage(
        (int) (totalCount + pageRequest.getPageSize() - 1) / pageRequest.getPageSize());
    return pageResponse;
  }

  public static <E, T> PageResponse<E> of(PageResponse<T> pageResponse, Function<T, E> function) {
    if (Objects.nonNull(pageResponse)) {
      List<T> data = pageResponse.getData();
      if (CollectionUtils.isNotEmpty(data)) {
        List<E> result =
            data.stream().filter(Objects::nonNull).map(function).collect(Collectors.toList());
        return of(pageResponse, result);
      }
    }
    return new PageResponse<>();
  }

  public static <E, T> PageResponse<E> of(PageResponse<T> pageResponse, List<E> list) {
    return PageResponse.<E>builder()
        .pageNo(pageResponse.getPageNo())
        .pageSize(pageResponse.getPageSize())
        .totalCount(pageResponse.getTotalCount())
        .totalPage(pageResponse.getTotalPage())
        .data(list)
        .build();
  }

  public static <T> PageResponse<T> empty() {
    return new PageResponse<>();
  }
}
