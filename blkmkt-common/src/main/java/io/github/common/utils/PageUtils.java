package io.github.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class PageUtils<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 总记录数
	 */
	@ApiModelProperty(name = "totalCount", value = "总记录数", example = "1")
	private int totalCount;
	/**
	 * 每页记录数
	 */
	@ApiModelProperty(name = "pageSize", value = "每页记录数", example = "10")
	private int pageSize;
	/**
	 * 总页数
	 */
	@ApiModelProperty(name = "totalPage", value = "总页数", example = "1")
	private int totalPage;
	/**
	 * 当前页数
	 */
	@ApiModelProperty(name = "currPage", value = "当前页数", example = "1")
	private int currPage;
	/**
	 * 列表数据
	 */
	@ApiModelProperty(name = "list", value = "集合")
	private List<T> list;

	/**
	 * 分页
	 * @param list        列表数据
	 * @param totalCount  总记录数
	 * @param pageSize    每页记录数
	 * @param currPage    当前页数
	 */
	public PageUtils(List<T> list, int totalCount, int pageSize, int currPage) {
		this.list = list;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.currPage = currPage;
		this.totalPage = (int)Math.ceil((double)totalCount/pageSize);
	}

	/**
	 * 分页
	 */
	public PageUtils(IPage<T> page) {
		this.list = page.getRecords();
		this.totalCount = (int)page.getTotal();
		this.pageSize = (int)page.getSize();
		this.currPage = (int)page.getCurrent();
		this.totalPage = (int)page.getPages();
	}
}
