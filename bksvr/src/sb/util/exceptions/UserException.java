package sb.util.exceptions;



public class UserException extends Exception {

       final private String msg;
       final private Integer exceptionCode;
       final private String severity;

       

    public UserException(Integer exceptionCode,String severity, String message) {

       this.exceptionCode = exceptionCode;

       this.severity = severity;

       this.msg = message;

    }



    

    public String getMessage() {

       return msg;

    }





       public Integer getExceptionCode() {

              return exceptionCode;

       }





       public String getSeverity() {

              return severity;

       }

    

    

}

