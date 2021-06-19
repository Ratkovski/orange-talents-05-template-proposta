package br.com.zupacademy.ratkovski.proposta.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class Cripto implements AttributeConverter <String,String>{

    @Value("${proposta.password}")
    private String password;
    @Value("${proposta.salt}")
    private String salt;

   /*  private static String password = "keycloak";
    private static String salt = "6B6579636C6F616B";

   @SuppressWarnings("deprecation")
   private static TextEncryptor textEncryptor = Encryptors.queryableText(password, salt);

    public static String encrypt(String documento) {
        return textEncryptor.encrypt(documento);
    }

    public static String decrypt(String documento) {
        return textEncryptor.decrypt(documento);
    }*/

    //métodos gerados automaticamente
    @Override
    public String convertToDatabaseColumn(String s) {
        return Encryptors.queryableText(password,salt).encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return Encryptors.queryableText(password,salt).decrypt(s);

    }



}
