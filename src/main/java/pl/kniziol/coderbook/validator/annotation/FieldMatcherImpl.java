package pl.kniziol.coderbook.validator.annotation;

import pl.kniziol.coderbook.exception.AppException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public class FieldMatcherImpl implements ConstraintValidator<FieldMatcher, Object> {

   private String firstFieldName;
   private String secondFieldName;
   private String message;

   public void initialize(FieldMatcher constraint) {
      this.firstFieldName = constraint.firstFieldName();
      this.secondFieldName = constraint.secondFieldName();
      this.message = constraint.message();
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      Object firstObj;
      Object secondObj;
      try {
         firstObj = obj.getClass().getDeclaredField(firstFieldName);
         secondObj = obj.getClass().getDeclaredField(secondFieldName);


      } catch (NoSuchFieldException  exception){
         throw new AppException(exception.getMessage());
      }
      char[] asdfsadf = new char[3];
      if (asdfsadf instanceof char[] asdf){
         System.out.println("asdfsadf");
      }

      Class<?> componentType = char[].class.getComponentType();
      Class<?> componentType1 = firstObj.getClass().arrayType();

      if (firstObj.getClass().getComponentType().equals(char[].class.getComponentType())) {
         System.out.println("dsafsdf");
      }
      if (firstObj instanceof char[] firstChars && secondObj instanceof char[] secondChars){
         boolean assa = Arrays.equals(firstChars, secondChars);
         return Arrays.equals(firstChars, secondChars);
      } else {
         return firstObj.equals(secondObj);
      }
   }

   private Object getObjectFromFieldName(Object obj, String fieldName) throws NoSuchFieldException, IllegalAccessException {
      Field field = obj.getClass().getField(fieldName);
      return field.get(obj);
   }
}
