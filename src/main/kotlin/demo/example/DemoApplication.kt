package demo.example

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableMetrics(proxyTargetClass = true)
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}
