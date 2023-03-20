// SLf4j 1.7 can accidentially be pulled in using Spring Boot 3 leading to exceptions
// https://github.com/spring-projects/spring-boot/issues/33854
@file:DependsOn("org.springframework.boot:spring-boot-starter-web:2.7.9")
// logging.level.org.springframework.boot.autoconfigure=INFO
//@file:CompilerOptions("-jvm-target", "1.8", "-Xabi-stability=unstable")
//@file:DependsOn("ch.qos.logback:logback-classic:1.3.4", options = ["transitive=false"])
//@file:DependsOn("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
package sample

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class HelloController {
    @GetMapping("/hello")
    fun helloKotlin(): String {
        return "hello world"
    }
}

@ComponentScan(basePackages = ["sample"])
@SpringBootApplication
open class ReplApplication

fun main(args: Array<String>) {
    SpringApplication(ReplApplication::class.java).run(*args)
}

main(arrayOf("--server.port=8083"))
