package com.blog.bean;

import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author pengli
 * @date 2021-08-10
 *
 * 博客在web层的传输对象
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogBean {

    /**
     * 内容
     */
    private String content;

    private Base64 base64;
}
