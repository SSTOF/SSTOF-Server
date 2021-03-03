package com.sstof.config.auth;

import com.sstof.auth.exception.GithubEmailNotPublicException;
import com.sstof.users.domain.User;
import com.sstof.users.domain.UserRole;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes ofGithub(String userNameAttributeName, Map<String, Object> attributes) {
        if(attributes.get("email") == null) {
            throw new GithubEmailNotPublicException("깃허브 링크에 Public Email이 없습니다. 설정 후 다시 시도해 주세요.");
        }
        return OAuthAttributes.builder()
                .name((String) attributes.get("login"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .role(UserRole.ROLE_USER)
                .build();
    }
}
