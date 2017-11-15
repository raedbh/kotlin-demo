package com.example.demo

import com.codahale.metrics.MetricFilter
import com.codahale.metrics.MetricRegistry
import com.codahale.metrics.graphite.Graphite
import com.codahale.metrics.graphite.GraphiteReporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct


@Configuration
class GraphiteConfig {

    @Autowired
    lateinit var registry: MetricRegistry

    @PostConstruct
    fun initialize() {
        val graphite = Graphite(InetSocketAddress("localhost", 2003))
        val reporter = GraphiteReporter.forRegistry(registry)
                .prefixedWith("demo1")
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .filter(MetricFilter.ALL)
                .build(graphite)
        reporter.start(1, TimeUnit.MINUTES)

    }

}