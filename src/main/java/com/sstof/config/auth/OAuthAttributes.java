package com.sstof.config.auth;

import com.sstof.auth.exception.GithubEmailNotPublicException;
import com.sstof.users.domain.User;
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
        System.out.println((String) attributes.get("name"));
        System.out.println((String) attributes.get("email"));
        attributes.forEach((object, index) -> System.out.println(object + ": " + attributes.get(object)));
        if(attributes.get("email") == null) {
            throw new GithubEmailNotPublicException();
        }
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .build();
    }
}
