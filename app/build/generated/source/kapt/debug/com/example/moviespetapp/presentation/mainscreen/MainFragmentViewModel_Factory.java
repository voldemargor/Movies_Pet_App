package com.example.moviespetapp.presentation.mainscreen;

import com.example.moviespetapp.domain.usecase.GetGenresUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenComedyMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenFictionMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenHorrorMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenKidMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenNewMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenPopularMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMainScreenSoonMoviesUseCase;
import com.example.moviespetapp.domain.usecase.GetMoviesByGenreUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainFragmentViewModel_Factory implements Factory<MainFragmentViewModel> {
  private final Provider<GetGenresUseCase> getGenresUseCaseProvider;

  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  private final Provider<GetMainScreenNewMoviesUseCase> getMainScreenNewMoviesUseCaseProvider;

  private final Provider<GetMainScreenSoonMoviesUseCase> getMainScreenSoonMoviesUseCaseProvider;

  private final Provider<GetMainScreenPopularMoviesUseCase> getMainScreenPopularMoviesUseCaseProvider;

  private final Provider<GetMainScreenFictionMoviesUseCase> getMainScreenFictionMoviesUseCaseProvider;

  private final Provider<GetMainScreenComedyMoviesUseCase> getMainScreenComedyMoviesUseCaseProvider;

  private final Provider<GetMainScreenHorrorMoviesUseCase> getMainScreenHorrorMoviesUseCaseProvider;

  private final Provider<GetMainScreenKidMoviesUseCase> getMainScreenKidMoviesUseCaseProvider;

  public MainFragmentViewModel_Factory(Provider<GetGenresUseCase> getGenresUseCaseProvider,
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider,
      Provider<GetMainScreenNewMoviesUseCase> getMainScreenNewMoviesUseCaseProvider,
      Provider<GetMainScreenSoonMoviesUseCase> getMainScreenSoonMoviesUseCaseProvider,
      Provider<GetMainScreenPopularMoviesUseCase> getMainScreenPopularMoviesUseCaseProvider,
      Provider<GetMainScreenFictionMoviesUseCase> getMainScreenFictionMoviesUseCaseProvider,
      Provider<GetMainScreenComedyMoviesUseCase> getMainScreenComedyMoviesUseCaseProvider,
      Provider<GetMainScreenHorrorMoviesUseCase> getMainScreenHorrorMoviesUseCaseProvider,
      Provider<GetMainScreenKidMoviesUseCase> getMainScreenKidMoviesUseCaseProvider) {
    this.getGenresUseCaseProvider = getGenresUseCaseProvider;
    this.getMoviesByGenreUseCaseProvider = getMoviesByGenreUseCaseProvider;
    this.getMainScreenNewMoviesUseCaseProvider = getMainScreenNewMoviesUseCaseProvider;
    this.getMainScreenSoonMoviesUseCaseProvider = getMainScreenSoonMoviesUseCaseProvider;
    this.getMainScreenPopularMoviesUseCaseProvider = getMainScreenPopularMoviesUseCaseProvider;
    this.getMainScreenFictionMoviesUseCaseProvider = getMainScreenFictionMoviesUseCaseProvider;
    this.getMainScreenComedyMoviesUseCaseProvider = getMainScreenComedyMoviesUseCaseProvider;
    this.getMainScreenHorrorMoviesUseCaseProvider = getMainScreenHorrorMoviesUseCaseProvider;
    this.getMainScreenKidMoviesUseCaseProvider = getMainScreenKidMoviesUseCaseProvider;
  }

  @Override
  public MainFragmentViewModel get() {
    MainFragmentViewModel instance = newInstance();
    MainFragmentViewModel_MembersInjector.injectGetGenresUseCase(instance, getGenresUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenNewMoviesUseCase(instance, getMainScreenNewMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenSoonMoviesUseCase(instance, getMainScreenSoonMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenPopularMoviesUseCase(instance, getMainScreenPopularMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenFictionMoviesUseCase(instance, getMainScreenFictionMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenComedyMoviesUseCase(instance, getMainScreenComedyMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenHorrorMoviesUseCase(instance, getMainScreenHorrorMoviesUseCaseProvider.get());
    MainFragmentViewModel_MembersInjector.injectGetMainScreenKidMoviesUseCase(instance, getMainScreenKidMoviesUseCaseProvider.get());
    return instance;
  }

  public static MainFragmentViewModel_Factory create(
      Provider<GetGenresUseCase> getGenresUseCaseProvider,
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider,
      Provider<GetMainScreenNewMoviesUseCase> getMainScreenNewMoviesUseCaseProvider,
      Provider<GetMainScreenSoonMoviesUseCase> getMainScreenSoonMoviesUseCaseProvider,
      Provider<GetMainScreenPopularMoviesUseCase> getMainScreenPopularMoviesUseCaseProvider,
      Provider<GetMainScreenFictionMoviesUseCase> getMainScreenFictionMoviesUseCaseProvider,
      Provider<GetMainScreenComedyMoviesUseCase> getMainScreenComedyMoviesUseCaseProvider,
      Provider<GetMainScreenHorrorMoviesUseCase> getMainScreenHorrorMoviesUseCaseProvider,
      Provider<GetMainScreenKidMoviesUseCase> getMainScreenKidMoviesUseCaseProvider) {
    return new MainFragmentViewModel_Factory(getGenresUseCaseProvider, getMoviesByGenreUseCaseProvider, getMainScreenNewMoviesUseCaseProvider, getMainScreenSoonMoviesUseCaseProvider, getMainScreenPopularMoviesUseCaseProvider, getMainScreenFictionMoviesUseCaseProvider, getMainScreenComedyMoviesUseCaseProvider, getMainScreenHorrorMoviesUseCaseProvider, getMainScreenKidMoviesUseCaseProvider);
  }

  public static MainFragmentViewModel newInstance() {
    return new MainFragmentViewModel();
  }
}
