package alltest;

import gui.SignIn;

public class SignUpTest {

    public static void main(String[] args) {
        SignUpTest test = new SignUpTest();
        
        // Run tests
        test.testSignUpSuccess();
        test.testSignUpFailureInvalidEmail();
        
        System.out.println("All tests completed.");
    }

    public void testSignUpSuccess() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        
        // Act
        boolean result = SignIn(email, password);
        
        // Assert
        if (result) {
            System.out.println("testSignUpSuccess: Passed");
        } else {
            System.out.println("testSignUpSuccess: Failed");
        }
    }

    public void testSignUpFailureInvalidEmail() {
        // Arrange
        String email = "invalid-email";
        String password = "password123";
        
        // Act
        boolean result = SignIn(email, password);
        
        // Assert
        if (!result) {
            System.out.println("testSignUpFailureInvalidEmail: Passed");
        } else {
            System.out.println("testSignUpFailureInvalidEmail: Failed");
        }
    }
    
}