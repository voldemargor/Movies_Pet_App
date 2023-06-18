package com.example.moviespetapp.domain;

import com.example.moviespetapp.domain.usecase.GetMainScreenPopularMoviesUseCase;

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
public final class GetMainScreenPopularMoviesUseCase_Factory implements Factory<GetMainScreenPopularMoviesUseCase> {
  private final Provider<Repository> repositoryProvider;

  public GetMainScreenPopularMoviesUseCase_Factory(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetMainScreenPopularMoviesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetMainScreenPopularMoviesUseCase_Factory create(
      Provider<Repository> repositoryProvider) {
    return new GetMainScreenPopularMoviesUseCase_Factory(repositoryProvider);
  }

  public static GetMainScreenPopularMoviesUseCase newInstance(Repository repository) {
    return new GetMainScreenPopularMoviesUseCase(repository);
  }
}
