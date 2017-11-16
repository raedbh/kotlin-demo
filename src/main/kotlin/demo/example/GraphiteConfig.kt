package demo.example

import com.codahale.metrics.MetricFilter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.graphite.Graphite
import com.codahale.metrics.graphite.GraphiteReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct


@Configuration
class GraphiteConfig {

    @Autowired
    lateinit var registry: MetricRegistry

    @Value("\${graphite.carbon.host}")
    lateinit var host: String

    @Value("\${graphite.carbon.port}")
    var port: Int = 0

    @Value("\${graphite.time-between-polls}")
    var period: Long = 0

    @PostConstruct
    fun initialize() {
        val graphite = Graphite(InetSocketAddress(host, port))
        val reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith("kotlin-demo")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite)
        reporter.start(period, TimeUnit.MINUTES)
    }

}