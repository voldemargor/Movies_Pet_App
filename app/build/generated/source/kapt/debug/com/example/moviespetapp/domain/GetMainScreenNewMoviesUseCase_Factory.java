package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenNewMoviesUseCase;

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
public final class GetMainScreenNewMoviesUseCase_Factory implements Factory<GetMainScreenNewMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenNewMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenNewMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenNewMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenNewMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenNewMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenNewMoviesUseCase(repository);
  }
}
