package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenHorrorMoviesUseCase;

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
public final class GetMainScreenHorrorMoviesUseCase_Factory implements Factory<GetMainScreenHorrorMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenHorrorMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenHorrorMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenHorrorMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenHorrorMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenHorrorMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenHorrorMoviesUseCase(repository);
  }
}
