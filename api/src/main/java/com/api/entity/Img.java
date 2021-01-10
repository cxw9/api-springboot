package com.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author o
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Img对象", description="")
public class Img implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid;

    private String type;

    private String url;

    private String md5;

    private Integer size;

    private Integer width;

    private Integer height;

    private String suf;

    private String gettime;

    private String updatetime;


}
