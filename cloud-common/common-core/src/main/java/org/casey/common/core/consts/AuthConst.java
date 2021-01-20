package org.casey.common.core.consts;

/**
 * @InterfaceName AuthConstant
 * @Author Fu Kai
 * @Description todo
 * @Date 2020/12/25 12:11
 */

public interface AuthConst {
    String AUTHORITY_PREFIX = "ROLE_";

    String AUTHORITY_CLAIM_NAME = "authorities";

    String AUTHORIZATION_HEADER = "Authorization";

    String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
}
