package com.demo.movies.di

import com.demo.movies.MoviesApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance // Bind our application instance to our Dagger graph.
        fun application(application: MoviesApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: MoviesApplication)
}
