package work.dduo.access_backend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * No.
     */
    @TableId
    private Long id;

    /**
     * 是否删除 '1 删除', '0 未删除'
     */
    private Integer isDelete;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别 '1男 Male', '2女 Female '
     */
    private String gender;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 邮件
     */
    private String email;

    /**
     * 状态  '0禁用 unable', '1可用 enable'
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updateAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}