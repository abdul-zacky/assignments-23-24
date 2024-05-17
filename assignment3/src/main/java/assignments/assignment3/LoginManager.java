package assignments.assignment3;

import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;
import assignments.assignment3.systemCLI.UserSystemCLI;

public class LoginManager {
    private final AdminSystemCLI adminSystem;
    private final CustomerSystemCLI customerSystem;

    public LoginManager(AdminSystemCLI adminSystem, CustomerSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

    public UserSystemCLI getSystem(String role) {
<<<<<<< HEAD
        if (role == "Customer") {
=======
        if (role.equals("Customer")) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            return customerSystem;
        } else {
            return adminSystem;
        }

        return adminSystem;
    }
}