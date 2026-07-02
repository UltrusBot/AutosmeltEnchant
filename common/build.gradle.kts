import me.ultrusmods.smeltingtouch.gradle.Properties

plugins {
    id("conventions.common")
    alias(libs.plugins.neoforge.moddev)
}

sourceSets {
    create("generated") {
        resources {
            srcDir("src/generated/resources")
        }
    }
}
neoForge {
    neoFormVersion = Properties.NEOFORM_VERSION
    addModdingDependenciesTo(sourceSets["test"])

    val at = file("src/main/resources/${Properties.MOD_ID}.cfg")
    if (at.exists())
        setAccessTransformers(at)
    validateAccessTransformers = true
}

dependencies {
    compileOnly(group = "org.spongepowered", name = "mixin", version = "0.8.5")
    compileOnly(libs.mixin.extras)
    annotationProcessor(libs.mixin.extras)
}

configurations {
    register("commonJava") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
    register("commonTestResources") {
        isCanBeResolved = false
        isCanBeConsumed = true
    }
}

artifacts {
    add("commonJava", sourceSets["main"].java.sourceDirectories.singleFile)
    add("commonResources", sourceSets["main"].resources.sourceDirectories.singleFile)
    add("commonResources", sourceSets["generated"].resources.sourceDirectories.singleFile)
    add("commonTestResources", sourceSets["test"].resources.sourceDirectories.singleFile)
}