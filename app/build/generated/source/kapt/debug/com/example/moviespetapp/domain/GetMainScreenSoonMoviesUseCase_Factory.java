package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenSoonMoviesUseCase;

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
public final class GetMainScreenSoonMoviesUseCase_Factory implements Factory<GetMainScreenSoonMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenSoonMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenSoonMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenSoonMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenSoonMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenSoonMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenSoonMoviesUseCase(repository);
  }
}
