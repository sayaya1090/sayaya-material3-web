plugins {
    id("java")
    id("org.docstr.gwt") version "1.1.31"
    id("maven-publish")
}
repositories {
    mavenCentral()
    mavenLocal()
}
group = "dev.sayaya"
version = "2.2.0"

dependencies {
    implementation("org.jboss.elemento:elemento-core:1.6.11")
    implementation("org.gwtproject:gwt-user:2.12.0")
    compileOnly("org.gwtproject:gwt-dev:2.12.0")
    implementation("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

gwt {
    minHeapSize = "1024M"
    maxHeapSize = "2048M"
    sourceLevel = "auto"
    val lombok: File = project.configurations.annotationProcessor.get().filter { it.name.startsWith("lombok") }.single()
    extraJvmArgs = listOf("-XX:ReservedCodeCacheSize=512M", "-javaagent:${lombok}=ECJ")
}

tasks {
    withType<Delete> { doFirst { delete("build/") } }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
}