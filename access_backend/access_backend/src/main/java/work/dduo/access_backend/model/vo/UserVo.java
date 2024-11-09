package work.dduo.access_backend.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {
    /**
     * No.
     */
    private Long id;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}