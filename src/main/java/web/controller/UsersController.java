package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    public UserService service;


    @Transactional
    @GetMapping(value = "/users")
    public String listAll(Model model) {

        User user1 = new User("Ivan", "Ivanov","email", 30);
        service.add(user1);

        List<User> users = service.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Age = " + user.getAge());
            System.out.println("E-mail = " + user.getEmail());
            System.out.println();
        }
        model.addAttribute("listOfUsers", users);
        return "users/hello_page";
    }

    @Transactional
    @GetMapping(value = "/create")
    public String createUser (Model create_model) {
        User user = new User();
        create_model.addAttribute("user",user);
        return "users/create_page";
    }

    @Transactional
    @GetMapping(value = "/delete")
    public String deleteUser (@RequestParam(value = "id", required = false) Long id, Model delete_model) {
        User user = service.readUserById(id);
        delete_model.addAttribute("user",user);
        System.out.println("delete id = " + id);
        return "users/delete_page";
    }



    @Transactional
    @GetMapping(value = "/read")
    public String readUser (@RequestParam(value = "id", required = false) Long id , Model read_model) {
        User user = service.readUserById(id);
        read_model.addAttribute("user",user);
        return "users/read_page";
    }

    @Transactional
//    @GetMapping("/update")
//    public String updateUserPage (@RequestParam(value = "id", required = false) Long id, Model edit_model) {
//        User user = service.readUserById(id);
//        edit_model.addAttribute("user",user);
//        System.out.println("Работа c данными update user" + user.getId());
//        return "users/update_page";
//        }


    @GetMapping(value = "/update/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.readUserById(id);
        System.out.println("работа /update/{id} ");
        model.addAttribute("user", user);
        return "users/update_page";
    }
//    @PostMapping("/update")
//    public String editUser(@ModelAttribute("user") User user) {
//        service.update(user);
//        return "redirect:/";
//    }

    //? нужен POST ??? какая аннотация??
    @RequestMapping(name = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println("Работа с данными");
        service.update(user);
        System.out.println("завершение работы с данными");
        return "users/hello_page";
    }
//////////////////
//@GetMapping("/edit/{id}")
//public String editUser(@PathVariable("id") int id, Model model) {
//    User user = userService.getById(id);
//    model.addAttribute("editUser", user);
//    return "userEdit";
//}
//    @PostMapping("/edit")
//    public String editUser(@ModelAttribute("editUser") User user) {
//        userService.edit(user);
//        return "redirect:/";
//    }


//    @Override
//    public void edit(User user) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.merge(user);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//    }
//
//<body>
//<div align="center">
//    <h1>Edit User</h1>
//    <form th:action="@{/edit}" th:object="${editUser}" method="post">
//        <table>
//            <!--<tr><p>Id: <input th:type="number" th:value="*{id}"></p></tr>-->
//            <tr><p>First_Name: <input th:type="text" th:field="*{firstName}"></p></tr>
//            <tr><p>Last_Name: <input th:type="text" th:field="*{lastName}"></p></tr>
//            <tr><p>Age: <input th:type="number" th:field="*{age}"></p></tr>
//            <tr><p>Email: <input th:type="text" th:field="*{email}"></p></tr>
//            <tr><p>Action: <input th:type="submit" th:value="Submit"></p></tr>
//        </table>
//    </form>
//    <a th:href="@{/}">Return</a>
//</div>
//</body>

///////////
}


