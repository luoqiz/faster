package org.wolf.security4.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luoqiz
 * @since 2019-04-25
 */
@Data
@Accessors(chain = true)
public class SysUser {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;


}
