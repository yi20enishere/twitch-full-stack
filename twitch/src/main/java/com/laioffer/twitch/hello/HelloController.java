package com.laioffer.twitch.hello;


import com.nimbusds.jwt.JWT;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import library


// 一个项目里面，可以有多个controller
@RestController
public class HelloController {

                // 这个annotation"/hello" 是在网址上的一部分
                // 代码所有的名称都应该和功能相符，不然很confuse
    @GetMapping("/hello")    // url中？后面的参数：@RequestParam(required = false) String name
                                // http://localhost:8080/hello?name=Raven
    // 每发一次请求，log里面可以打印出来
    // @GetMapping("/hello")
    //public String sayHello() {
    //    System.out.println("Hello World!");
    //     return "Hello World!";
    //}
    public Person sayHello(@RequestParam(required = false) String name) {
        // 返回一个person
        // 运行：http://localhost:8080/hello，最后在网页上会返回一个json文件：
//        {
//            "name": "John",
//                "company": "Doe",
//                "homeAddress": {
//            "street": "123 Main St",
//                    "city": "Anytown",
//                    "state": "NY",
//                    "country": "USA"
//          },
//            "favoriteBook": {
//            "title": "The Art of War",
//                    "author": "John Smith"
//          }
//        }
        Faker faker = new Faker();  // dataFaker这个库提供的东西，然后每次生成的json信息都是随机不一样的动态信息
        if (name == null) {
            name = "Guest";
        }
        // String name = faker.name().fullName();
        String company = faker.company().name();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String bookTitle = faker.book().title();
        String bookAuthor = faker.book().author();
        // 因为build.Gradle里面添加了dataFaker，就可以动态显示
        return new Person(name, company, new Address(street, city, state, null), new Book(bookTitle, bookAuthor));
                                                        // 如果只给了部分实用的信息，那就最后只显示有用信息
                                                        // 不要显示空的信息，否则会拖慢响应时间
    }
}

// RESTful API: An API that follows REST (Representational State Transfer) principles over HTTP
// GET /users → list users (safe, cacheable)
//GET /users/42 → get one user
//POST /users → create user
//PUT /users/42 → replace user
//PATCH /users/42 → partial update
//DELETE /users/42 → delete user

// “stateless”: No server memory of client state between requests.
// Each request is self-contained and must include everything the server needs:
// auth credentials (e.g., bearer token), locale, pagination params, correlation IDs, etc.
// 服务器不记前情：服务器不会在内存里“记住”某个客户端之前访问到哪一步、谁已经登录、购物车里有啥等“会话状态”。
// 每个请求都要自带上下文：每一次请求都必须自己带齐服务器处理所需的信息，
// 比如：身份凭证（Bearer Token/JWT）、分页参数、语言设置、幂等键等。服务器仅凭这一条请求就能完成处理，不需要翻旧账。
// example:
// 状态无记忆（Stateless）像去图书馆办事：每次来都出示证件和材料，窗口不认识你，但凭这次材料照样办成。
// 有记忆像办理长流程业务：柜台记着你“昨天走到第3步”，今天来只报号就继续。