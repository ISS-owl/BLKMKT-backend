package io.github.blkmkt.forum.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class GoodModel {
    private static final long serialVersionUID = 1L;

    /**
     * 货物id
     */
    @TableId
    @ApiModelProperty(name = "id", value = "货物id")
    private Integer id;
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
     * 是否有货
     */
    @ApiModelProperty(name = "hasStock", value = "是否有货")
    private Integer hasStock;
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
    /**
     * 标题
     */
    @ApiModelProperty(name = "title", value = "标题")
    private String title;
    /**
     * 副标题
     */
    @ApiModelProperty(name = "subtitle", value = "副标题")
    private String subtitle;
}
