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
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainFragmentViewModel_MembersInjector implements MembersInjector<MainFragmentViewModel> {
  private final Provider<GetGenresUseCase> getGenresUseCaseProvider;

  private final Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider;

  private final Provider<GetMainScreenNewMoviesUseCase> getMainScreenNewMoviesUseCaseProvider;

  private final Provider<GetMainScreenSoonMoviesUseCase> getMainScreenSoonMoviesUseCaseProvider;

  private final Provider<GetMainScreenPopularMoviesUseCase> getMainScreenPopularMoviesUseCaseProvider;

  private final Provider<GetMainScreenFictionMoviesUseCase> getMainScreenFictionMoviesUseCaseProvider;

  private final Provider<GetMainScreenComedyMoviesUseCase> getMainScreenComedyMoviesUseCaseProvider;

  private final Provider<GetMainScreenHorrorMoviesUseCase> getMainScreenHorrorMoviesUseCaseProvider;

  private final Provider<GetMainScreenKidMoviesUseCase> getMainScreenKidMoviesUseCaseProvider;

  public MainFragmentViewModel_MembersInjector(Provider<GetGenresUseCase> getGenresUseCaseProvider,
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

  public static MembersInjector<MainFragmentViewModel> create(
      Provider<GetGenresUseCase> getGenresUseCaseProvider,
      Provider<GetMoviesByGenreUseCase> getMoviesByGenreUseCaseProvider,
      Provider<GetMainScreenNewMoviesUseCase> getMainScreenNewMoviesUseCaseProvider,
      Provider<GetMainScreenSoonMoviesUseCase> getMainScreenSoonMoviesUseCaseProvider,
      Provider<GetMainScreenPopularMoviesUseCase> getMainScreenPopularMoviesUseCaseProvider,
      Provider<GetMainScreenFictionMoviesUseCase> getMainScreenFictionMoviesUseCaseProvider,
      Provider<GetMainScreenComedyMoviesUseCase> getMainScreenComedyMoviesUseCaseProvider,
      Provider<GetMainScreenHorrorMoviesUseCase> getMainScreenHorrorMoviesUseCaseProvider,
      Provider<GetMainScreenKidMoviesUseCase> getMainScreenKidMoviesUseCaseProvider) {
    return new MainFragmentViewModel_MembersInjector(getGenresUseCaseProvider, getMoviesByGenreUseCaseProvider, getMainScreenNewMoviesUseCaseProvider, getMainScreenSoonMoviesUseCaseProvider, getMainScreenPopularMoviesUseCaseProvider, getMainScreenFictionMoviesUseCaseProvider, getMainScreenComedyMoviesUseCaseProvider, getMainScreenHorrorMoviesUseCaseProvider, getMainScreenKidMoviesUseCaseProvider);
  }

  @Override
  public void injectMembers(MainFragmentViewModel instance) {
    injectGetGenresUseCase(instance, getGenresUseCaseProvider.get());
    injectGetMoviesByGenreUseCase(instance, getMoviesByGenreUseCaseProvider.get());
    injectGetMainScreenNewMoviesUseCase(instance, getMainScreenNewMoviesUseCaseProvider.get());
    injectGetMainScreenSoonMoviesUseCase(instance, getMainScreenSoonMoviesUseCaseProvider.get());
    injectGetMainScreenPopularMoviesUseCase(instance, getMainScreenPopularMoviesUseCaseProvider.get());
    injectGetMainScreenFictionMoviesUseCase(instance, getMainScreenFictionMoviesUseCaseProvider.get());
    injectGetMainScreenComedyMoviesUseCase(instance, getMainScreenComedyMoviesUseCaseProvider.get());
    injectGetMainScreenHorrorMoviesUseCase(instance, getMainScreenHorrorMoviesUseCaseProvider.get());
    injectGetMainScreenKidMoviesUseCase(instance, getMainScreenKidMoviesUseCaseProvider.get());
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getGenresUseCase")
  public static void injectGetGenresUseCase(MainFragmentViewModel instance,
      GetGenresUseCase getGenresUseCase) {
    instance.getGenresUseCase = getGenresUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMoviesByGenreUseCase")
  public static void injectGetMoviesByGenreUseCase(MainFragmentViewModel instance,
      GetMoviesByGenreUseCase getMoviesByGenreUseCase) {
    instance.getMoviesByGenreUseCase = getMoviesByGenreUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenNewMoviesUseCase")
  public static void injectGetMainScreenNewMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenNewMoviesUseCase getMainScreenNewMoviesUseCase) {
    instance.getMainScreenNewMoviesUseCase = getMainScreenNewMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenSoonMoviesUseCase")
  public static void injectGetMainScreenSoonMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenSoonMoviesUseCase getMainScreenSoonMoviesUseCase) {
    instance.getMainScreenSoonMoviesUseCase = getMainScreenSoonMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenPopularMoviesUseCase")
  public static void injectGetMainScreenPopularMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenPopularMoviesUseCase getMainScreenPopularMoviesUseCase) {
    instance.getMainScreenPopularMoviesUseCase = getMainScreenPopularMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenFictionMoviesUseCase")
  public static void injectGetMainScreenFictionMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenFictionMoviesUseCase getMainScreenFictionMoviesUseCase) {
    instance.getMainScreenFictionMoviesUseCase = getMainScreenFictionMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenComedyMoviesUseCase")
  public static void injectGetMainScreenComedyMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenComedyMoviesUseCase getMainScreenComedyMoviesUseCase) {
    instance.getMainScreenComedyMoviesUseCase = getMainScreenComedyMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenHorrorMoviesUseCase")
  public static void injectGetMainScreenHorrorMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenHorrorMoviesUseCase getMainScreenHorrorMoviesUseCase) {
    instance.getMainScreenHorrorMoviesUseCase = getMainScreenHorrorMoviesUseCase;
  }

  @InjectedFieldSignature("com.example.moviespetapp.presentation.mainscreen.MainFragmentViewModel.getMainScreenKidMoviesUseCase")
  public static void injectGetMainScreenKidMoviesUseCase(MainFragmentViewModel instance,
      GetMainScreenKidMoviesUseCase getMainScreenKidMoviesUseCase) {
    instance.getMainScreenKidMoviesUseCase = getMainScreenKidMoviesUseCase;
  }
}
