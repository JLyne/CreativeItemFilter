import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
	`maven-publish`
    alias(libs.plugins.pluginYmlPaper)
}

group = "org.hurricanegames"
version = "3.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

repositories {
	mavenLocal()
	mavenCentral()
    maven {
        url = uri("https://repo.not-null.co.uk/snapshots/")
    }
	maven {
		url = uri("https://repo.papermc.io/repository/maven-public/")
	}
}

dependencies {
	compileOnly(libs.paperApi)
    paperLibrary(libs.messagesHelper)
}

paper {
    main = "org.hurricanegames.creativeitemfilter.CreativeItemFilter"
    loader = "org.hurricanegames.creativeitemfilter.CreativeItemFilterLoader"
    generateLibrariesJson = true
    apiVersion = libs.versions.paperApiVersion.get().replace(Regex("\\-R\\d.\\d-SNAPSHOT"), "")
    authors = listOf("Jim (AnEnragedPigeon)", "_Shevchik_")

    permissions {
        register("creativeitemfilter.reload") {
            description = "Allows reloading the configuration"
            default = BukkitPluginDescription.Permission.Default.OP
        }
        register("creativeitemfilter.bypass") {
            description = "Bypasses all item checks"
            default = BukkitPluginDescription.Permission.Default.FALSE
            children = listOf("creativeitemfilter.bypass.filter", "creativeitemfilter.bypass.blacklist")
        }
        register("creativeitemfilter.bypass.filter") {
            description = "Bypasses the ItemMeta filtering"
            default = BukkitPluginDescription.Permission.Default.FALSE
        }
        register("creativeitemfilter.bypass.blacklist") {
            description = "Bypasses the item blacklist"
            default = BukkitPluginDescription.Permission.Default.OP
        }
    }
}

tasks {
    compileJava {
        options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
        options.encoding = "UTF-8"
    }
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components.getByName("java"))
            pom {
                description = "CreativeItemFilter"
                url = "https://github.com/JLyne/CreativeItemFilter"
                developers {
                    developer {
                        id = "jim"
                        name = "James Lyne"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/JLyne/CreativeItemFilter.git"
                    developerConnection = "scm:git:ssh://github.com/JLyne/CreativeItemFilter.git"
                    url = "https://github.com/JLyne/CreativeItemFilter"
                }
            }
        }
    }
    repositories {
        maven {
            name = "notnull"
            credentials(PasswordCredentials::class)
            val releasesRepoUrl = uri("https://repo.not-null.co.uk/releases/") // gradle -Prelease publish
            val snapshotsRepoUrl = uri("https://repo.not-null.co.uk/snapshots/")
            url = if (project.hasProperty("release")) releasesRepoUrl else snapshotsRepoUrl
        }
    }
}