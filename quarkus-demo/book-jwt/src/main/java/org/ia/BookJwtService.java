package org.ia;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static io.smallrye.jwt.build.Jwt.groups;

@Singleton
public class BookJwtService {

    public String generateToken(){
        Set<String> roles=new HashSet<>(Arrays.asList("admin","writer"));

       return Jwt.issuer("book-jwt")
               .subject("Book")
               .groups(roles)
               .expiresAt(System.currentTimeMillis()+3600)
               .sign();
    }

}
