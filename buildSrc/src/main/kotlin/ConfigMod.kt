import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class ConfigMod(private val project: Project) {

    private val libs: VersionCatalog = project
        .extensions
        .getByType<VersionCatalogsExtension>()
        .named("libs")

    /**
     * Add common dependencies to this project
     */
    fun commonDependencies(): ConfigMod {
        project.dependencies {
            "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
            "implementation"(libs.findLibrary("androidx.core.ktx").get())
            "implementation"(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
            "implementation"(libs.findLibrary("androidx.compose.runtime").get())
            "implementation"(libs.findLibrary("guava").get())
        }
        return this
    }

    /**
     * Add all compose dependencies to this project
     */
    fun composeDependencies(): ConfigMod {
        project.dependencies {
            "implementation"(libs.findLibrary("androidx.compose.ui").get())
            "implementation"(libs.findLibrary("androidx.compose.ui.graphics").get())
            "implementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
            "implementation"(libs.findLibrary("androidx.compose.lifecycle").get())
            "implementation"(libs.findLibrary("androidx.compose.activity").get())
            "implementation"(libs.findLibrary("androidx.compose.navigation").get())
            "implementation"(libs.findLibrary("androidx.compose.material3").get())
            "implementation"(libs.findLibrary("androidx.compose.material3.window").get())
        }
        return this
    }

    /**
     * Add debug compose dependencies to this project
     */
    fun debugDependencies(): ConfigMod {
        project.dependencies {
            "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
            "debugImplementation"(libs.findLibrary("androidx.compose.ui.test.manifest").get())
        }
        return this
    }

    /**
     * Add test dependencies to this project
     */
    fun testDependencies(): ConfigMod {
        project.dependencies {
            "testImplementation"(libs.findLibrary("junit").get())
            "testImplementation"(libs.findLibrary("truth").get())
            "testImplementation"(libs.findLibrary("mockito.core").get())
            "testImplementation"(libs.findLibrary("coroutine.test").get())
            "testImplementation"(libs.findLibrary("koin.test").get())
            "testImplementation"(libs.findLibrary("koin.test.junit").get())
        }
        return this
    }

    /**
     * Add android test dependencies to this project
     */
    fun androidTestDependencies(): ConfigMod {
        project.dependencies {
            "androidTestImplementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
            "androidTestImplementation"(libs.findLibrary("androidx.test.junit").get())
            "androidTestImplementation"(libs.findLibrary("androidx.test.runner").get())
            "androidTestImplementation"(libs.findLibrary("androidx.test.core").get())
            "androidTestImplementation"(libs.findLibrary("androidx.test.rules").get())
            "androidTestImplementation"(libs.findLibrary("androidx.test.espresso.core").get())
            "androidTestImplementation"(libs.findLibrary("androidx.compose.ui.test.junit4").get())
            "androidTestImplementation"(libs.findLibrary("truth").get())
            "androidTestImplementation"(libs.findLibrary("mockito.core").get())
            "androidTestImplementation"(libs.findLibrary("mockito.dexmaker").get())
            "androidTestImplementation"(libs.findLibrary("koin.test").get())
            "androidTestImplementation"(libs.findLibrary("koin.test.junit").get())
            "androidTestImplementation"(libs.findLibrary("coroutine.test").get())
            "androidTestImplementation"(libs.findLibrary("junit.compose").get())
        }
        return this
    }
}
