package io.github.blkmkt.good.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 货物表
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-12 08:43:15
 */
@Data
@TableName("good")
public class GoodEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 货物id
     */
    @TableId
    @ApiModelProperty(name = "id", value = "货物id")
    private Integer id;
    /**
     * 货物名称
     */
    @ApiModelProperty(name = "name", value = "货物名称")
    private String name;
    /**
     * 卖家id
     */
    @ApiModelProperty(name = "ownerId", value = "卖家id")
    private Integer ownerId;
    /**
     * 价格
     */
    @ApiModelProperty(name = "price", value = "价格")
    private Integer price;
    /**
     * 出货收货[0 - 出货, 1 - 收货]
     */
    @ApiModelProperty(name = "gtype", value = "出货收货[0 - 出货, 1 - 收货]")
    private Integer gtype;
    /**
     * 分类
     */
    @ApiModelProperty(name = "category", value = "分类")
    private String category;
    /**
     * 总数
     */
    @ApiModelProperty(name = "totalNum", value = "总数")
    private Integer totalNum;
    /**
     * 当前数量
     */
    @ApiModelProperty(name = "currentNum", value = "当前数量")
    private Integer currentNum;
    /**
     * 点赞数
     */
    @ApiModelProperty(name = "like", value = "点赞数")
    private Integer likeNum;
    /**
     * 商品图片的url
     */
    @ApiModelProperty(name = "goodImgUrl", value = "商品图片的url")
    private String goodImgUrl;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateTime", value = "更新时间")
    private Date updateTime;

}
