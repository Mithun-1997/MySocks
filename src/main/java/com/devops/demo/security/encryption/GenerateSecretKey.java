package com.devops.demo.security.encryption;

import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import javax.crypto.SecretKey;

public class GenerateSecretKey {

    private static final String SECRET_KEY_PATH = "src/main/resources/security/jwt-secret.key";

    public static void main(String[] args) {
        try {
            generateAndStoreKey();
        } catch (IOException e) {
            System.err.println("Error generating secret key: " + e.getMessage());
        }
    }

    public static void generateAndStoreKey() throws IOException {
        SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String encodedKey = Encoders.BASE64.encode(key.getEncoded());

        Path path = Path.of(SECRET_KEY_PATH);
        Files.createDirectories(path.getParent());
        Files.writeString(path, encodedKey, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println("✅ Secret key generated and saved at: " + SECRET_KEY_PATH);
    }
}
