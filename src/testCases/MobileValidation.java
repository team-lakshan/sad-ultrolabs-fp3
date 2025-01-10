package testCases;

public class MobileValidation {

    public boolean validateMobile(String number) {
        return number.matches("^07[01245678]{1}[0-9]{7}$");
    }

}
