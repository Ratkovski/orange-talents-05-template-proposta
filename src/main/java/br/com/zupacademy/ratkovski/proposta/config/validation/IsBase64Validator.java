package br.com.zupacademy.ratkovski.proposta.config.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;


public class IsBase64Validator implements ConstraintValidator<IsBase64, String> {

    @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null)
            return false;

        try {
            /*pega o que a foi colocado ja em base64 e decodifica*/
           String decodificada = new String(Base64.getDecoder().decode(value));
           /*após decodificado ele codifica denovo*/
           String codificada = Base64.getEncoder().encodeToString(decodificada.getBytes());
            return  value.equals(codificada);


            //Base64.getDecoder().decode(value.getBytes());
             //deixa passar
          //  DatatypeConverter.parseBase64Binary(value);




        } catch (Exception e) {
            return false;
        }
//return true;
    }
}
